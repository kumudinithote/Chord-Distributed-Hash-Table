/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2020-10-18")
public class RFile implements org.apache.thrift.TBase<RFile, RFile._Fields>, java.io.Serializable, Cloneable, Comparable<RFile> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RFile");

  private static final org.apache.thrift.protocol.TField META_FIELD_DESC = new org.apache.thrift.protocol.TField("meta", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField CONTENT_FIELD_DESC = new org.apache.thrift.protocol.TField("content", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RFileStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RFileTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable RFileMetadata meta; // optional
  public @org.apache.thrift.annotation.Nullable java.lang.String content; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    META((short)1, "meta"),
    CONTENT((short)2, "content");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // META
          return META;
        case 2: // CONTENT
          return CONTENT;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.META,_Fields.CONTENT};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.META, new org.apache.thrift.meta_data.FieldMetaData("meta", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, RFileMetadata.class)));
    tmpMap.put(_Fields.CONTENT, new org.apache.thrift.meta_data.FieldMetaData("content", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RFile.class, metaDataMap);
  }

  public RFile() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RFile(RFile other) {
    if (other.isSetMeta()) {
      this.meta = new RFileMetadata(other.meta);
    }
    if (other.isSetContent()) {
      this.content = other.content;
    }
  }

  public RFile deepCopy() {
    return new RFile(this);
  }

  @Override
  public void clear() {
    this.meta = null;
    this.content = null;
  }

  @org.apache.thrift.annotation.Nullable
  public RFileMetadata getMeta() {
    return this.meta;
  }

  public RFile setMeta(@org.apache.thrift.annotation.Nullable RFileMetadata meta) {
    this.meta = meta;
    return this;
  }

  public void unsetMeta() {
    this.meta = null;
  }

  /** Returns true if field meta is set (has been assigned a value) and false otherwise */
  public boolean isSetMeta() {
    return this.meta != null;
  }

  public void setMetaIsSet(boolean value) {
    if (!value) {
      this.meta = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getContent() {
    return this.content;
  }

  public RFile setContent(@org.apache.thrift.annotation.Nullable java.lang.String content) {
    this.content = content;
    return this;
  }

  public void unsetContent() {
    this.content = null;
  }

  /** Returns true if field content is set (has been assigned a value) and false otherwise */
  public boolean isSetContent() {
    return this.content != null;
  }

  public void setContentIsSet(boolean value) {
    if (!value) {
      this.content = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case META:
      if (value == null) {
        unsetMeta();
      } else {
        setMeta((RFileMetadata)value);
      }
      break;

    case CONTENT:
      if (value == null) {
        unsetContent();
      } else {
        setContent((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case META:
      return getMeta();

    case CONTENT:
      return getContent();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case META:
      return isSetMeta();
    case CONTENT:
      return isSetContent();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RFile)
      return this.equals((RFile)that);
    return false;
  }

  public boolean equals(RFile that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_meta = true && this.isSetMeta();
    boolean that_present_meta = true && that.isSetMeta();
    if (this_present_meta || that_present_meta) {
      if (!(this_present_meta && that_present_meta))
        return false;
      if (!this.meta.equals(that.meta))
        return false;
    }

    boolean this_present_content = true && this.isSetContent();
    boolean that_present_content = true && that.isSetContent();
    if (this_present_content || that_present_content) {
      if (!(this_present_content && that_present_content))
        return false;
      if (!this.content.equals(that.content))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetMeta()) ? 131071 : 524287);
    if (isSetMeta())
      hashCode = hashCode * 8191 + meta.hashCode();

    hashCode = hashCode * 8191 + ((isSetContent()) ? 131071 : 524287);
    if (isSetContent())
      hashCode = hashCode * 8191 + content.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RFile other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetMeta()).compareTo(other.isSetMeta());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMeta()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.meta, other.meta);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetContent()).compareTo(other.isSetContent());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetContent()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.content, other.content);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RFile(");
    boolean first = true;

    if (isSetMeta()) {
      sb.append("meta:");
      if (this.meta == null) {
        sb.append("null");
      } else {
        sb.append(this.meta);
      }
      first = false;
    }
    if (isSetContent()) {
      if (!first) sb.append(", ");
      sb.append("content:");
      if (this.content == null) {
        sb.append("null");
      } else {
        sb.append(this.content);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (meta != null) {
      meta.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RFileStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RFileStandardScheme getScheme() {
      return new RFileStandardScheme();
    }
  }

  private static class RFileStandardScheme extends org.apache.thrift.scheme.StandardScheme<RFile> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RFile struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // META
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.meta = new RFileMetadata();
              struct.meta.read(iprot);
              struct.setMetaIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CONTENT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.content = iprot.readString();
              struct.setContentIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RFile struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.meta != null) {
        if (struct.isSetMeta()) {
          oprot.writeFieldBegin(META_FIELD_DESC);
          struct.meta.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.content != null) {
        if (struct.isSetContent()) {
          oprot.writeFieldBegin(CONTENT_FIELD_DESC);
          oprot.writeString(struct.content);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RFileTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RFileTupleScheme getScheme() {
      return new RFileTupleScheme();
    }
  }

  private static class RFileTupleScheme extends org.apache.thrift.scheme.TupleScheme<RFile> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RFile struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetMeta()) {
        optionals.set(0);
      }
      if (struct.isSetContent()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetMeta()) {
        struct.meta.write(oprot);
      }
      if (struct.isSetContent()) {
        oprot.writeString(struct.content);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RFile struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.meta = new RFileMetadata();
        struct.meta.read(iprot);
        struct.setMetaIsSet(true);
      }
      if (incoming.get(1)) {
        struct.content = iprot.readString();
        struct.setContentIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

