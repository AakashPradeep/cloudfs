// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cloudfs.proto

package com.aakash.cloudfs.protocol.proto.generated.stubs;

/**
 * Protobuf type {@code cloudfs.protocol.CFSFileStatusMap}
 */
public  final class CFSFileStatusMap extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:cloudfs.protocol.CFSFileStatusMap)
    CFSFileStatusMapOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CFSFileStatusMap.newBuilder() to construct.
  private CFSFileStatusMap(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CFSFileStatusMap() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new CFSFileStatusMap();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CFSFileStatusMap(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              cfsPathToFileStatusMap_ = com.google.protobuf.MapField.newMapField(
                  CfsPathToFileStatusMapDefaultEntryHolder.defaultEntry);
              mutable_bitField0_ |= 0x00000001;
            }
            com.google.protobuf.MapEntry<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>
            cfsPathToFileStatusMap__ = input.readMessage(
                CfsPathToFileStatusMapDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
            cfsPathToFileStatusMap_.getMutableMap().put(
                cfsPathToFileStatusMap__.getKey(), cfsPathToFileStatusMap__.getValue());
            break;
          }
          case 18: {
            com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg.Builder subBuilder = null;
            if (errorMsg_ != null) {
              subBuilder = errorMsg_.toBuilder();
            }
            errorMsg_ = input.readMessage(com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(errorMsg_);
              errorMsg_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_CFSFileStatusMap_descriptor;
  }

  @SuppressWarnings({"rawtypes"})
  @java.lang.Override
  protected com.google.protobuf.MapField internalGetMapField(
      int number) {
    switch (number) {
      case 1:
        return internalGetCfsPathToFileStatusMap();
      default:
        throw new RuntimeException(
            "Invalid map field number: " + number);
    }
  }
  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_CFSFileStatusMap_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap.class, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap.Builder.class);
  }

  public static final int CFSPATHTOFILESTATUSMAP_FIELD_NUMBER = 1;
  private static final class CfsPathToFileStatusMapDefaultEntryHolder {
    static final com.google.protobuf.MapEntry<
        java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> defaultEntry =
            com.google.protobuf.MapEntry
            .<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>newDefaultInstance(
                com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_CFSFileStatusMap_CfsPathToFileStatusMapEntry_descriptor, 
                com.google.protobuf.WireFormat.FieldType.STRING,
                "",
                com.google.protobuf.WireFormat.FieldType.MESSAGE,
                com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus.getDefaultInstance());
  }
  private com.google.protobuf.MapField<
      java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> cfsPathToFileStatusMap_;
  private com.google.protobuf.MapField<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>
  internalGetCfsPathToFileStatusMap() {
    if (cfsPathToFileStatusMap_ == null) {
      return com.google.protobuf.MapField.emptyMapField(
          CfsPathToFileStatusMapDefaultEntryHolder.defaultEntry);
    }
    return cfsPathToFileStatusMap_;
  }

  public int getCfsPathToFileStatusMapCount() {
    return internalGetCfsPathToFileStatusMap().getMap().size();
  }
  /**
   * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
   */

  public boolean containsCfsPathToFileStatusMap(
      java.lang.String key) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    return internalGetCfsPathToFileStatusMap().getMap().containsKey(key);
  }
  /**
   * Use {@link #getCfsPathToFileStatusMapMap()} instead.
   */
  @java.lang.Deprecated
  public java.util.Map<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> getCfsPathToFileStatusMap() {
    return getCfsPathToFileStatusMapMap();
  }
  /**
   * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
   */

  public java.util.Map<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> getCfsPathToFileStatusMapMap() {
    return internalGetCfsPathToFileStatusMap().getMap();
  }
  /**
   * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
   */

  public com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus getCfsPathToFileStatusMapOrDefault(
      java.lang.String key,
      com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus defaultValue) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    java.util.Map<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> map =
        internalGetCfsPathToFileStatusMap().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
   */

  public com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus getCfsPathToFileStatusMapOrThrow(
      java.lang.String key) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    java.util.Map<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> map =
        internalGetCfsPathToFileStatusMap().getMap();
    if (!map.containsKey(key)) {
      throw new java.lang.IllegalArgumentException();
    }
    return map.get(key);
  }

  public static final int ERRORMSG_FIELD_NUMBER = 2;
  private com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg errorMsg_;
  /**
   * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
   * @return Whether the errorMsg field is set.
   */
  public boolean hasErrorMsg() {
    return errorMsg_ != null;
  }
  /**
   * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
   * @return The errorMsg.
   */
  public com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg getErrorMsg() {
    return errorMsg_ == null ? com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg.getDefaultInstance() : errorMsg_;
  }
  /**
   * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
   */
  public com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsgOrBuilder getErrorMsgOrBuilder() {
    return getErrorMsg();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    com.google.protobuf.GeneratedMessageV3
      .serializeStringMapTo(
        output,
        internalGetCfsPathToFileStatusMap(),
        CfsPathToFileStatusMapDefaultEntryHolder.defaultEntry,
        1);
    if (errorMsg_ != null) {
      output.writeMessage(2, getErrorMsg());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (java.util.Map.Entry<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> entry
         : internalGetCfsPathToFileStatusMap().getMap().entrySet()) {
      com.google.protobuf.MapEntry<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>
      cfsPathToFileStatusMap__ = CfsPathToFileStatusMapDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, cfsPathToFileStatusMap__);
    }
    if (errorMsg_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getErrorMsg());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap)) {
      return super.equals(obj);
    }
    com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap other = (com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap) obj;

    if (!internalGetCfsPathToFileStatusMap().equals(
        other.internalGetCfsPathToFileStatusMap())) return false;
    if (hasErrorMsg() != other.hasErrorMsg()) return false;
    if (hasErrorMsg()) {
      if (!getErrorMsg()
          .equals(other.getErrorMsg())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (!internalGetCfsPathToFileStatusMap().getMap().isEmpty()) {
      hash = (37 * hash) + CFSPATHTOFILESTATUSMAP_FIELD_NUMBER;
      hash = (53 * hash) + internalGetCfsPathToFileStatusMap().hashCode();
    }
    if (hasErrorMsg()) {
      hash = (37 * hash) + ERRORMSG_FIELD_NUMBER;
      hash = (53 * hash) + getErrorMsg().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code cloudfs.protocol.CFSFileStatusMap}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:cloudfs.protocol.CFSFileStatusMap)
      com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMapOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_CFSFileStatusMap_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 1:
          return internalGetCfsPathToFileStatusMap();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMutableMapField(
        int number) {
      switch (number) {
        case 1:
          return internalGetMutableCfsPathToFileStatusMap();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_CFSFileStatusMap_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap.class, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap.Builder.class);
    }

    // Construct using com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      internalGetMutableCfsPathToFileStatusMap().clear();
      if (errorMsgBuilder_ == null) {
        errorMsg_ = null;
      } else {
        errorMsg_ = null;
        errorMsgBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_CFSFileStatusMap_descriptor;
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap getDefaultInstanceForType() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap.getDefaultInstance();
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap build() {
      com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap buildPartial() {
      com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap result = new com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap(this);
      int from_bitField0_ = bitField0_;
      result.cfsPathToFileStatusMap_ = internalGetCfsPathToFileStatusMap();
      result.cfsPathToFileStatusMap_.makeImmutable();
      if (errorMsgBuilder_ == null) {
        result.errorMsg_ = errorMsg_;
      } else {
        result.errorMsg_ = errorMsgBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap) {
        return mergeFrom((com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap other) {
      if (other == com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap.getDefaultInstance()) return this;
      internalGetMutableCfsPathToFileStatusMap().mergeFrom(
          other.internalGetCfsPathToFileStatusMap());
      if (other.hasErrorMsg()) {
        mergeErrorMsg(other.getErrorMsg());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.MapField<
        java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> cfsPathToFileStatusMap_;
    private com.google.protobuf.MapField<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>
    internalGetCfsPathToFileStatusMap() {
      if (cfsPathToFileStatusMap_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            CfsPathToFileStatusMapDefaultEntryHolder.defaultEntry);
      }
      return cfsPathToFileStatusMap_;
    }
    private com.google.protobuf.MapField<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>
    internalGetMutableCfsPathToFileStatusMap() {
      onChanged();;
      if (cfsPathToFileStatusMap_ == null) {
        cfsPathToFileStatusMap_ = com.google.protobuf.MapField.newMapField(
            CfsPathToFileStatusMapDefaultEntryHolder.defaultEntry);
      }
      if (!cfsPathToFileStatusMap_.isMutable()) {
        cfsPathToFileStatusMap_ = cfsPathToFileStatusMap_.copy();
      }
      return cfsPathToFileStatusMap_;
    }

    public int getCfsPathToFileStatusMapCount() {
      return internalGetCfsPathToFileStatusMap().getMap().size();
    }
    /**
     * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
     */

    public boolean containsCfsPathToFileStatusMap(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      return internalGetCfsPathToFileStatusMap().getMap().containsKey(key);
    }
    /**
     * Use {@link #getCfsPathToFileStatusMapMap()} instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> getCfsPathToFileStatusMap() {
      return getCfsPathToFileStatusMapMap();
    }
    /**
     * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
     */

    public java.util.Map<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> getCfsPathToFileStatusMapMap() {
      return internalGetCfsPathToFileStatusMap().getMap();
    }
    /**
     * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
     */

    public com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus getCfsPathToFileStatusMapOrDefault(
        java.lang.String key,
        com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus defaultValue) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> map =
          internalGetCfsPathToFileStatusMap().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
     */

    public com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus getCfsPathToFileStatusMapOrThrow(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> map =
          internalGetCfsPathToFileStatusMap().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    public Builder clearCfsPathToFileStatusMap() {
      internalGetMutableCfsPathToFileStatusMap().getMutableMap()
          .clear();
      return this;
    }
    /**
     * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
     */

    public Builder removeCfsPathToFileStatusMap(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      internalGetMutableCfsPathToFileStatusMap().getMutableMap()
          .remove(key);
      return this;
    }
    /**
     * Use alternate mutation accessors instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>
    getMutableCfsPathToFileStatusMap() {
      return internalGetMutableCfsPathToFileStatusMap().getMutableMap();
    }
    /**
     * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
     */
    public Builder putCfsPathToFileStatusMap(
        java.lang.String key,
        com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus value) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      if (value == null) { throw new java.lang.NullPointerException(); }
      internalGetMutableCfsPathToFileStatusMap().getMutableMap()
          .put(key, value);
      return this;
    }
    /**
     * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
     */

    public Builder putAllCfsPathToFileStatusMap(
        java.util.Map<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> values) {
      internalGetMutableCfsPathToFileStatusMap().getMutableMap()
          .putAll(values);
      return this;
    }

    private com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg errorMsg_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg, com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg.Builder, com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsgOrBuilder> errorMsgBuilder_;
    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
     * @return Whether the errorMsg field is set.
     */
    public boolean hasErrorMsg() {
      return errorMsgBuilder_ != null || errorMsg_ != null;
    }
    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
     * @return The errorMsg.
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg getErrorMsg() {
      if (errorMsgBuilder_ == null) {
        return errorMsg_ == null ? com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg.getDefaultInstance() : errorMsg_;
      } else {
        return errorMsgBuilder_.getMessage();
      }
    }
    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
     */
    public Builder setErrorMsg(com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg value) {
      if (errorMsgBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        errorMsg_ = value;
        onChanged();
      } else {
        errorMsgBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
     */
    public Builder setErrorMsg(
        com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg.Builder builderForValue) {
      if (errorMsgBuilder_ == null) {
        errorMsg_ = builderForValue.build();
        onChanged();
      } else {
        errorMsgBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
     */
    public Builder mergeErrorMsg(com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg value) {
      if (errorMsgBuilder_ == null) {
        if (errorMsg_ != null) {
          errorMsg_ =
            com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg.newBuilder(errorMsg_).mergeFrom(value).buildPartial();
        } else {
          errorMsg_ = value;
        }
        onChanged();
      } else {
        errorMsgBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
     */
    public Builder clearErrorMsg() {
      if (errorMsgBuilder_ == null) {
        errorMsg_ = null;
        onChanged();
      } else {
        errorMsg_ = null;
        errorMsgBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg.Builder getErrorMsgBuilder() {
      
      onChanged();
      return getErrorMsgFieldBuilder().getBuilder();
    }
    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsgOrBuilder getErrorMsgOrBuilder() {
      if (errorMsgBuilder_ != null) {
        return errorMsgBuilder_.getMessageOrBuilder();
      } else {
        return errorMsg_ == null ?
            com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg.getDefaultInstance() : errorMsg_;
      }
    }
    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg, com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg.Builder, com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsgOrBuilder> 
        getErrorMsgFieldBuilder() {
      if (errorMsgBuilder_ == null) {
        errorMsgBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg, com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg.Builder, com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsgOrBuilder>(
                getErrorMsg(),
                getParentForChildren(),
                isClean());
        errorMsg_ = null;
      }
      return errorMsgBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:cloudfs.protocol.CFSFileStatusMap)
  }

  // @@protoc_insertion_point(class_scope:cloudfs.protocol.CFSFileStatusMap)
  private static final com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap();
  }

  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CFSFileStatusMap>
      PARSER = new com.google.protobuf.AbstractParser<CFSFileStatusMap>() {
    @java.lang.Override
    public CFSFileStatusMap parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CFSFileStatusMap(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CFSFileStatusMap> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CFSFileStatusMap> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

