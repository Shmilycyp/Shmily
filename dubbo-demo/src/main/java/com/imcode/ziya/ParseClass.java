package com.imcode.ziya;

import cn.hutool.core.lang.Assert;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-06-01 21:45
 */
public class ParseClass {

    private static final List<ConstantPollParse> PARSES = new ArrayList<ConstantPollParse>(){{
        add(new CONSTANT_Class_info());
        add(new CONSTANT_Methodref_info());
        add(new CONSTANT_Utf8_info());
        add(new CONTANT_NameAndType_info());
        add(new CONSTANT_Fieldref_info());
    }};


    private static final List<AttributeParse> ATTRIBUTE_PARSES  = new ArrayList<AttributeParse>() {{

        add(new Deprecated_Attribute_Info());
        add(new RuntimeVisableAnnotationsAttribute());

    }};

    // 65 - 90
    public static void main(String[] args) throws IOException {

        ParseClass parseClass = new ParseClass("D:\\IDEA\\git-workspace\\Shmily\\dubbo-demo\\target\\classes\\com\\imcode\\ziya\\A.class");

        parseClass.parse();


    }

    String filePath;
    File targetFile;
    RandomAccessFile raf;
    FileChannel channel;
    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(64);
    private ParseClassResult result = new ParseClassResult();

    @SneakyThrows
    public ParseClass(String filePath) {
        this.filePath = filePath;
        targetFile = new File(filePath);
        if (!targetFile.exists()) {
            throw new RuntimeException("target file not exists");
        }
        raf = new RandomAccessFile(targetFile, "r");
        channel = raf.getChannel();
        channel.read(byteBuffer);
        byteBuffer.flip();
    }

    public ParseClassResult parse() throws IOException {
        checkMagic();
        buildVersion();
        buildConstantPoolCount();
        parseConstantPoll();
        parseAccessFlags();
        parseThisClass();
        parseSuperClass();
        parserInterface();
        parseField();

        return result;
    }

    private void parseMethod() throws IOException {
        short method_count = getShort();
        if (method_count == 0) {
            return;
        }




    }

    private void parseField() throws IOException {
        short field_count = getShort();
        if (field_count == 0) {
            return;
        }
        for (int i = 0; i < (int) field_count; i++) {
            Field_Info field_info = new Field_Info();
            field_info.setAccess_flags(getShort());
            field_info.setName_index(getShort());
            field_info.setDescriptor_index(getShort());
            field_info.setAttributes_count(getShort());
            // parse attribute
            if (field_info.getAttributes_count() != 0) {
                Attribute_Info[] attribute_infos = new Attribute_Info[field_info.getAttributes_count()];
                for (int j = 0; j < (int) field_info.getAttributes_count(); j++) {
                    attribute_infos[j] = parserAttribute();
                }
                field_info.setAttribute_infos(attribute_infos);
            }
        }
    }

    private Attribute_Info parserAttribute() throws IOException {

        Attribute_Info info = new Attribute_Info();
        info.setAttribute_name_index(getShort());
        info.setAttribute_length(getInt());
        Optional<AttributeParse> targetParse = ATTRIBUTE_PARSES.stream()
                .filter(f -> f.match(info, result)).findAny();
        if (!targetParse.isPresent()) {
            throw new RuntimeException("can not match attribute for " + info);
        }
        return targetParse.get().newInstance(info, this);
    }

    private void parserInterface() throws IOException {
        short interface_count = getShort();
        if (interface_count == 0) {
            return;
        }
        CONSTANT_Class_info[] interfaces = new CONSTANT_Class_info[interface_count];
        for (int i = 0; i < (int) interface_count; i++) {
            interfaces[i] = (CONSTANT_Class_info) result.cp_info[getShort() - 1];
        }
        result.setInterfaces(interfaces);
    }

    private void parseSuperClass() throws IOException {
        result.setSuper_class(getShort());
    }

    private void parseThisClass() throws IOException {
        result.setThis_class(getShort());
    }

    private void parseAccessFlags() throws IOException {
        ParseClassResult.ACCESS_FLAG_INFO access_flag_info = new ParseClassResult.ACCESS_FLAG_INFO(getShort());
        result.setAccess_flag_info(access_flag_info);
    }

    private void parseConstantPoll() throws IOException {
        int constant_pool_count = result.getConstant_pool_count();

        ConstantClassInfo[] constantClassInfos = new ConstantClassInfo[constant_pool_count];

        for (int i = 0; i < constant_pool_count - 1; i++) {
            byte[] aByte = getByte(1);
            ConstantClassInfo parse = PARSES.stream().filter(f -> f.match(aByte[0])).findFirst()
                    .get().parse(this);
            constantClassInfos[i] = parse;
        }
        result.setCp_info(constantClassInfos);
    }

    private void buildConstantPoolCount() throws IOException {
        short aShort = getShort();
        System.out.println("常量池大小为:" + aShort);
        result.setConstant_pool_count(aShort);
    }

    private void buildVersion() throws IOException {
        // u2 minor_version
        short minor_version = getShort();
        // u2 major_version
        short major_version = getShort();
        System.out.println("主版本号:" + major_version + " 次版本号:" + minor_version);
        result.setMajor_version(major_version);
        result.setMinor_version(minor_version);
    }

    private void checkMagic() throws IOException {
        int anInt = getInt();
        Assert.isTrue(Integer.toHexString(anInt).equals("cafebabe"));
        System.out.println("head is right");
    }

    public short getShort() throws IOException {
        checkRead(2);
        return byteBuffer.getShort();
    }

    public char getChar() throws IOException {
        checkRead(2);
        return byteBuffer.getChar();
    }

    public int getInt() throws IOException {
        checkRead(4);
        return byteBuffer.getInt();
    }

    public byte[] getByte(int num) throws IOException {
        checkRead(num);
        byte[] bytes = new byte[num];
        byteBuffer.get(bytes);
        return bytes;
    }

    private void checkRead(int size) throws IOException {
        // 容量
        int limit = byteBuffer.limit();
        // 当前的位置
        int position = byteBuffer.position();

        if (limit - position < size) {
            byte[] bytes = new byte[limit - position];
            ByteBuffer byteBuffer = this.byteBuffer.get(bytes);
            byteBuffer.flip();
            if (bytes.length != 0) {
                byteBuffer.put(bytes);
            }
            channel.read(byteBuffer);
            byteBuffer.flip();
        }
    }

    private interface Initialization {

        void init(ParseClass parseClass) throws IOException;
    }

    @Data
    public static class ParseClassResult {

        private short minor_version;
        private short major_version;
        private short constant_pool_count;
        // 常量池内的内容
        private ConstantClassInfo[] cp_info;
        private ACCESS_FLAG_INFO access_flag_info;
        private short this_class;
        // super_class index
        private short super_class;
        private CONSTANT_Class_info[] interfaces;
        

        public String getSuperClassStr () {
            CONSTANT_Class_info constantClassInfo = (CONSTANT_Class_info) cp_info[super_class - 1];
            CONSTANT_Utf8_info constant_utf8_info = (CONSTANT_Utf8_info) cp_info[constantClassInfo.name_index - 1];
            return constant_utf8_info.getInfo();
        }

        public String getThisClassStr () {
            CONSTANT_Class_info constantClassInfo = (CONSTANT_Class_info) cp_info[this_class - 1];
            CONSTANT_Utf8_info constant_utf8_info = (CONSTANT_Utf8_info) cp_info[constantClassInfo.name_index - 1];
            return constant_utf8_info.getInfo();
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class ACCESS_FLAG_INFO {

            private short flag;

            public boolean isPublic() {
                return (ACC.ACC_PUBLIC.value & flag) != 0;
            }

            public boolean isFinal() {
                return (ACC.ACC_FINAL.value & flag) != 0;
            }

            @Getter
            public enum ACC {

                ACC_PUBLIC((short) 0x0001),
                ACC_FINAL((short) 0x0010),
                ACC_SUPER((short) 0x0020),
                ACC_INTERFACE((short) 0x0200),
                ACC_ABSTRACT((short) 0x0400),
                ACC_SYNTHETIC((short) 0x1000),
                ACC_ANNOTATION((short) 0x2000),
                ACC_ENUM((short) 0x4000),
                ;
                private short value;

                ACC(short value) {
                    this.value = value;
                }
            }

        }

    }

    @Data
    public static class Method_Info implements Initialization {
        private short access_flags;
        private short name_index;
        private short descriptor_index;
        private short attribute_count;
        private Attribute_Info[] attribute_info;


        @Override
        public void init(ParseClass parseClass) throws IOException {
            this.access_flags = parseClass.getShort();
            this.name_index = parseClass.getShort();
            this.descriptor_index = parseClass.getShort();
            this.attribute_count = parseClass.getShort();
            if (this.attribute_count != 0) {
                attribute_info = new Attribute_Info[attribute_count];
                for (int i = 0; i < (int) attribute_count; i++) {
                    attribute_info[i] = parseClass.parserAttribute();
                }
            }

        }
    }

    @Data
    public static class Field_Info {

        private short access_flags;
        private short name_index;
        private short descriptor_index;
        private short attributes_count;
        private Attribute_Info[] attribute_infos;

    }

    /**
     * 属性信息
     */
    @Data
    public static class Attribute_Info {

        private short attribute_name_index;
        private int attribute_length;

    }

    public interface AttributeParse {

        boolean match(Attribute_Info attribute_info, ParseClassResult parseClassResult);

        Attribute_Info newInstance(Attribute_Info attribute_info, ParseClass parseClass) throws IOException ;
    }

    /**
     * Deprecated 注解
     *   index值必须是对常量池的一个有效索引，为Deprecated，且length = 0
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Deprecated_Attribute_Info extends Attribute_Info implements AttributeParse{

        public Deprecated_Attribute_Info() {
        }

        public Deprecated_Attribute_Info(short attribute_name_index, int attribute_length) {
            super.setAttribute_name_index(attribute_name_index);
            super.setAttribute_length(attribute_length);
        }

        @Override
        public boolean match(Attribute_Info attribute_info, ParseClassResult parseClassResult) {

            ConstantClassInfo constantClassInfo = parseClassResult.cp_info[attribute_info.attribute_name_index - 1];
            if (constantClassInfo instanceof CONSTANT_Utf8_info) {
                CONSTANT_Utf8_info constant_utf8_info = (CONSTANT_Utf8_info) constantClassInfo;
                return constant_utf8_info.getInfo().equals("Deprecated") && attribute_info.attribute_length == 0;
            }
            return false;
        }

        @Override
        public Attribute_Info newInstance(Attribute_Info attribute_info, ParseClass parseClass) {
            return new Deprecated_Attribute_Info(attribute_info.attribute_name_index, attribute_info.attribute_length);
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class RuntimeVisableAnnotationsAttribute extends Attribute_Info implements AttributeParse {

        // 当前结构所表示的运行时可见的注解数量
        private short num_annotations;
        private Annotation[] annotations;

        @Override
        public boolean match(Attribute_Info attribute_info, ParseClassResult parseClassResult) {

            ConstantClassInfo constantClassInfo = parseClassResult.cp_info[attribute_info.attribute_name_index - 1];
            if (constantClassInfo instanceof CONSTANT_Utf8_info) {
                CONSTANT_Utf8_info constant_utf8_info = (CONSTANT_Utf8_info) constantClassInfo;
                return constant_utf8_info.getInfo().equals("RuntimeVisibleAnnotations");
            }
            return false;
        }

        @Override
        public Attribute_Info newInstance(Attribute_Info attribute_info, ParseClass parseClass) throws IOException {
            RuntimeVisableAnnotationsAttribute result = new RuntimeVisableAnnotationsAttribute();

            result.setAttribute_name_index(attribute_info.getAttribute_name_index());
            result.setAttribute_length(attribute_info.attribute_length);
            result.setNum_annotations(parseClass.getShort());
            if (result.getNum_annotations() != 0) {

                Annotation[] annotations = new Annotation[result.num_annotations];
                for (int i = 0; i < (int) result.num_annotations; i++) {
                    annotations[i] = new Annotation(parseClass);
                }
                result.setAnnotations(annotations);
            }
            return result;
        }

        @Data
        public static class Annotation {

            // 常量池的索引
            private short type_index;
            // 注解的键值对大小
            private short num_element_value_pairs;

            private Element_Value_Pair[] pairs;


            public Annotation(ParseClass parseClass) throws IOException {
                this.type_index = parseClass.getShort();
                this.num_element_value_pairs = parseClass.getShort();
                if (this.num_element_value_pairs  != 0) {
                    pairs = new Element_Value_Pair[num_element_value_pairs];
                    for (int i = 0; i < (int) num_element_value_pairs; i++) {
                        pairs[i] = new Element_Value_Pair(parseClass);
                    }
                }
            }
        }

        @Data
        public static class Element_Value_Pair {

            // 常量池索引
            private short element_name_index;
            // 对应的值
            private Element_Value element_value;

            public Element_Value_Pair(ParseClass parseClass) throws IOException {
                this.element_name_index = parseClass.getShort();
                this.element_value = new Element_Value(parseClass.getByte(1)[0], parseClass);
            }
        }

        @Data
        public static class Element_Value {

            private byte tag;

            private Element element;

            public Element_Value(byte tag, ParseClass parseClass) throws IOException {
                this.tag = tag;
                switch (tag) {
                    case 'B' :
                    case 'C' :
                    case 'D' :
                    case 'F' :
                    case 'I' :
                    case 'J' :
                    case 'S' :
                    case 'Z' :
                    case 's' :
                        element = new const_value(parseClass.getShort());
                        break;
                    case 'e' :
                        element = new enum_const_value(parseClass.getShort(), parseClass.getShort());
                        break;
                    case 'c' :
                        element = new class_info_value(parseClass.getShort());
                        break;
                    case '@' :
                        element = new annotation_value(parseClass);
                        break;
                    case '[' :
                        element = new array_value(parseClass);
                        break;
                    default:
                        throw new RuntimeException("not match tag :" + tag);
                }
            }
        }


        public interface Element {

        }

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class const_value implements Element {
            private short const_value_index;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class enum_const_value implements Element {

            private short type_name_index;
            private short const_name_index;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class class_info_value implements Element {

            private short class_info_index;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class annotation_value implements Element {

            // 套娃
            private Annotation annotation;

            public annotation_value(ParseClass parseClass) throws IOException {
                this.annotation = new Annotation(parseClass);
            }
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class array_value implements Element {
            private short num_values;
            private Element_Value[] element_value;

            public array_value(ParseClass parseClass) throws IOException {
                this.num_values = parseClass.getShort();
                if (num_values != 0) {
                    element_value = new Element_Value[num_values];
                    for (int i = 0; i < (int) num_values; i++) {
                        element_value[i] = new Element_Value(parseClass.getByte(1)[0], parseClass);
                    }
                }

            }
        }
    }



    public interface ConstantPollParse {

        boolean match(byte value);

        ConstantClassInfo parse(ParseClass parseClass) throws IOException;
    }

    public interface ConstantClassInfo {

    }

    @Data
    public static class CONSTANT_Class_info implements ConstantPollParse, ConstantClassInfo {

        private static final byte tag = 7;
        private short name_index;

        @Override
        public boolean match(byte value) {
            return value == tag;
        }

        @Override
        public ConstantClassInfo parse(ParseClass parseClass) throws IOException {
            CONSTANT_Class_info constant_class_info = new CONSTANT_Class_info();
            constant_class_info.setName_index(parseClass.getShort());
            return constant_class_info;
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CONSTANT_Methodref_info implements ConstantPollParse, ConstantClassInfo {

        private short class_index;
        private short name_and_type_index;

        @Override
        public boolean match(byte value) {
            return value == 10;
        }

        @Override
        public ConstantClassInfo parse(ParseClass parseClass) throws IOException {
            return new CONSTANT_Methodref_info(parseClass.getShort(), parseClass.getShort());
        }
    }

    @NoArgsConstructor
    @Data
    public static class CONSTANT_Utf8_info implements ConstantPollParse, ConstantClassInfo {

        private short length;
        private byte[] bytes;
        private String info;

        public CONSTANT_Utf8_info(short length, byte[] bytes) {
            this.length = length;
            this.bytes = bytes;
            this.info = new String(this.bytes);
        }

        @Override
        public boolean match(byte value) {
            return value == 1;
        }

        @Override
        public ConstantClassInfo parse(ParseClass parseClass) throws IOException {
            short aShort = parseClass.getShort();
            return new CONSTANT_Utf8_info(aShort, parseClass.getByte(aShort));
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class CONTANT_NameAndType_info implements ConstantPollParse, ConstantClassInfo {

        private short name_index;
        private short descriptor_index;

        @Override
        public boolean match(byte value) {
             return value == 12;
        }

        @Override
        public CONTANT_NameAndType_info parse(ParseClass parseClass) throws IOException {
            return new CONTANT_NameAndType_info(parseClass.getShort(), parseClass.getShort());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CONSTANT_Fieldref_info implements ConstantPollParse, ConstantClassInfo {

        private short class_index;
        private short name_and_type_index;


        @Override
        public boolean match(byte value) {
            return value == 9;
        }

        @Override
        public ConstantClassInfo parse(ParseClass parseClass) throws IOException {
            return new CONSTANT_Fieldref_info(parseClass.getShort(), parseClass.getShort());
        }
    }

}
