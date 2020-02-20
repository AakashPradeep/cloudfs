// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cloudfs.proto

package com.aakash.cloudfs.protocol.proto.generated.stubs;

/**
 * Protobuf type {@code cloudfs.protocol.DirReq}
 */
public  final class DirReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:cloudfs.protocol.DirReq)
    DirReqOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DirReq.newBuilder() to construct.
  private DirReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DirReq() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new DirReq();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DirReq(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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
            com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder subBuilder = null;
            if (fsPath_ != null) {
              subBuilder = fsPath_.toBuilder();
            }
            fsPath_ = input.readMessage(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(fsPath_);
              fsPath_ = subBuilder.buildPartial();
            }

            break;
          }
          case 16: {

            permission_ = input.readInt32();
            break;
          }
          case 24: {

            reqTimeOutInMillis_ = input.readInt64();
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
    return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_DirReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_DirReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq.class, com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq.Builder.class);
  }

  public static final int FSPATH_FIELD_NUMBER = 1;
  private com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq fsPath_;
  /**
   * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
   * @return Whether the fsPath field is set.
   */
  public boolean hasFsPath() {
    return fsPath_ != null;
  }
  /**
   * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
   * @return The fsPath.
   */
  public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq getFsPath() {
    return fsPath_ == null ? com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.getDefaultInstance() : fsPath_;
  }
  /**
   * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
   */
  public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder getFsPathOrBuilder() {
    return getFsPath();
  }

  public static final int PERMISSION_FIELD_NUMBER = 2;
  private int permission_;
  /**
   * <code>int32 permission = 2;</code>
   * @return The permission.
   */
  public int getPermission() {
    return permission_;
  }

  public static final int REQTIMEOUTINMILLIS_FIELD_NUMBER = 3;
  private long reqTimeOutInMillis_;
  /**
   * <code>int64 reqTimeOutInMillis = 3;</code>
   * @return The reqTimeOutInMillis.
   */
  public long getReqTimeOutInMillis() {
    return reqTimeOutInMillis_;
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
    if (fsPath_ != null) {
      output.writeMessage(1, getFsPath());
    }
    if (permission_ != 0) {
      output.writeInt32(2, permission_);
    }
    if (reqTimeOutInMillis_ != 0L) {
      output.writeInt64(3, reqTimeOutInMillis_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (fsPath_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getFsPath());
    }
    if (permission_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, permission_);
    }
    if (reqTimeOutInMillis_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, reqTimeOutInMillis_);
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
    if (!(obj instanceof com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq)) {
      return super.equals(obj);
    }
    com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq other = (com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq) obj;

    if (hasFsPath() != other.hasFsPath()) return false;
    if (hasFsPath()) {
      if (!getFsPath()
          .equals(other.getFsPath())) return false;
    }
    if (getPermission()
        != other.getPermission()) return false;
    if (getReqTimeOutInMillis()
        != other.getReqTimeOutInMillis()) return false;
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
    if (hasFsPath()) {
      hash = (37 * hash) + FSPATH_FIELD_NUMBER;
      hash = (53 * hash) + getFsPath().hashCode();
    }
    hash = (37 * hash) + PERMISSION_FIELD_NUMBER;
    hash = (53 * hash) + getPermission();
    hash = (37 * hash) + REQTIMEOUTINMILLIS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getReqTimeOutInMillis());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq parseFrom(
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
  public static Builder newBuilder(com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq prototype) {
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
   * Protobuf type {@code cloudfs.protocol.DirReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:cloudfs.protocol.DirReq)
      com.aakash.cloudfs.protocol.proto.generated.stubs.DirReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_DirReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_DirReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq.class, com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq.Builder.class);
    }

    // Construct using com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq.newBuilder()
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
      if (fsPathBuilder_ == null) {
        fsPath_ = null;
      } else {
        fsPath_ = null;
        fsPathBuilder_ = null;
      }
      permission_ = 0;

      reqTimeOutInMillis_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_DirReq_descriptor;
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq getDefaultInstanceForType() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq.getDefaultInstance();
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq build() {
      com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq buildPartial() {
      com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq result = new com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq(this);
      if (fsPathBuilder_ == null) {
        result.fsPath_ = fsPath_;
      } else {
        result.fsPath_ = fsPathBuilder_.build();
      }
      result.permission_ = permission_;
      result.reqTimeOutInMillis_ = reqTimeOutInMillis_;
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
      if (other instanceof com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq) {
        return mergeFrom((com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq other) {
      if (other == com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq.getDefaultInstance()) return this;
      if (other.hasFsPath()) {
        mergeFsPath(other.getFsPath());
      }
      if (other.getPermission() != 0) {
        setPermission(other.getPermission());
      }
      if (other.getReqTimeOutInMillis() != 0L) {
        setReqTimeOutInMillis(other.getReqTimeOutInMillis());
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
      com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq fsPath_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder> fsPathBuilder_;
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
     * @return Whether the fsPath field is set.
     */
    public boolean hasFsPath() {
      return fsPathBuilder_ != null || fsPath_ != null;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
     * @return The fsPath.
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq getFsPath() {
      if (fsPathBuilder_ == null) {
        return fsPath_ == null ? com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.getDefaultInstance() : fsPath_;
      } else {
        return fsPathBuilder_.getMessage();
      }
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
     */
    public Builder setFsPath(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq value) {
      if (fsPathBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        fsPath_ = value;
        onChanged();
      } else {
        fsPathBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
     */
    public Builder setFsPath(
        com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder builderForValue) {
      if (fsPathBuilder_ == null) {
        fsPath_ = builderForValue.build();
        onChanged();
      } else {
        fsPathBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
     */
    public Builder mergeFsPath(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq value) {
      if (fsPathBuilder_ == null) {
        if (fsPath_ != null) {
          fsPath_ =
            com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.newBuilder(fsPath_).mergeFrom(value).buildPartial();
        } else {
          fsPath_ = value;
        }
        onChanged();
      } else {
        fsPathBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
     */
    public Builder clearFsPath() {
      if (fsPathBuilder_ == null) {
        fsPath_ = null;
        onChanged();
      } else {
        fsPath_ = null;
        fsPathBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder getFsPathBuilder() {
      
      onChanged();
      return getFsPathFieldBuilder().getBuilder();
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder getFsPathOrBuilder() {
      if (fsPathBuilder_ != null) {
        return fsPathBuilder_.getMessageOrBuilder();
      } else {
        return fsPath_ == null ?
            com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.getDefaultInstance() : fsPath_;
      }
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder> 
        getFsPathFieldBuilder() {
      if (fsPathBuilder_ == null) {
        fsPathBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder>(
                getFsPath(),
                getParentForChildren(),
                isClean());
        fsPath_ = null;
      }
      return fsPathBuilder_;
    }

    private int permission_ ;
    /**
     * <code>int32 permission = 2;</code>
     * @return The permission.
     */
    public int getPermission() {
      return permission_;
    }
    /**
     * <code>int32 permission = 2;</code>
     * @param value The permission to set.
     * @return This builder for chaining.
     */
    public Builder setPermission(int value) {
      
      permission_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 permission = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearPermission() {
      
      permission_ = 0;
      onChanged();
      return this;
    }

    private long reqTimeOutInMillis_ ;
    /**
     * <code>int64 reqTimeOutInMillis = 3;</code>
     * @return The reqTimeOutInMillis.
     */
    public long getReqTimeOutInMillis() {
      return reqTimeOutInMillis_;
    }
    /**
     * <code>int64 reqTimeOutInMillis = 3;</code>
     * @param value The reqTimeOutInMillis to set.
     * @return This builder for chaining.
     */
    public Builder setReqTimeOutInMillis(long value) {
      
      reqTimeOutInMillis_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 reqTimeOutInMillis = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearReqTimeOutInMillis() {
      
      reqTimeOutInMillis_ = 0L;
      onChanged();
      return this;
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


    // @@protoc_insertion_point(builder_scope:cloudfs.protocol.DirReq)
  }

  // @@protoc_insertion_point(class_scope:cloudfs.protocol.DirReq)
  private static final com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq();
  }

  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DirReq>
      PARSER = new com.google.protobuf.AbstractParser<DirReq>() {
    @java.lang.Override
    public DirReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DirReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DirReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DirReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

