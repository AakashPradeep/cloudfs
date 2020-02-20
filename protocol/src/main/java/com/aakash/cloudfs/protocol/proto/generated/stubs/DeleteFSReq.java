// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cloudfs.proto

package com.aakash.cloudfs.protocol.proto.generated.stubs;

/**
 * Protobuf type {@code cloudfs.protocol.DeleteFSReq}
 */
public  final class DeleteFSReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:cloudfs.protocol.DeleteFSReq)
    DeleteFSReqOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DeleteFSReq.newBuilder() to construct.
  private DeleteFSReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DeleteFSReq() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new DeleteFSReq();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DeleteFSReq(
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
            if (fsPathReq_ != null) {
              subBuilder = fsPathReq_.toBuilder();
            }
            fsPathReq_ = input.readMessage(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(fsPathReq_);
              fsPathReq_ = subBuilder.buildPartial();
            }

            break;
          }
          case 16: {

            recursive_ = input.readBool();
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
    return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_DeleteFSReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_DeleteFSReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq.class, com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq.Builder.class);
  }

  public static final int FSPATHREQ_FIELD_NUMBER = 1;
  private com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq fsPathReq_;
  /**
   * <code>.cloudfs.protocol.FSPathReq fsPathReq = 1;</code>
   * @return Whether the fsPathReq field is set.
   */
  public boolean hasFsPathReq() {
    return fsPathReq_ != null;
  }
  /**
   * <code>.cloudfs.protocol.FSPathReq fsPathReq = 1;</code>
   * @return The fsPathReq.
   */
  public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq getFsPathReq() {
    return fsPathReq_ == null ? com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.getDefaultInstance() : fsPathReq_;
  }
  /**
   * <code>.cloudfs.protocol.FSPathReq fsPathReq = 1;</code>
   */
  public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder getFsPathReqOrBuilder() {
    return getFsPathReq();
  }

  public static final int RECURSIVE_FIELD_NUMBER = 2;
  private boolean recursive_;
  /**
   * <code>bool recursive = 2;</code>
   * @return The recursive.
   */
  public boolean getRecursive() {
    return recursive_;
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
    if (fsPathReq_ != null) {
      output.writeMessage(1, getFsPathReq());
    }
    if (recursive_ != false) {
      output.writeBool(2, recursive_);
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
    if (fsPathReq_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getFsPathReq());
    }
    if (recursive_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(2, recursive_);
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
    if (!(obj instanceof com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq)) {
      return super.equals(obj);
    }
    com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq other = (com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq) obj;

    if (hasFsPathReq() != other.hasFsPathReq()) return false;
    if (hasFsPathReq()) {
      if (!getFsPathReq()
          .equals(other.getFsPathReq())) return false;
    }
    if (getRecursive()
        != other.getRecursive()) return false;
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
    if (hasFsPathReq()) {
      hash = (37 * hash) + FSPATHREQ_FIELD_NUMBER;
      hash = (53 * hash) + getFsPathReq().hashCode();
    }
    hash = (37 * hash) + RECURSIVE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getRecursive());
    hash = (37 * hash) + REQTIMEOUTINMILLIS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getReqTimeOutInMillis());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq parseFrom(
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
  public static Builder newBuilder(com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq prototype) {
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
   * Protobuf type {@code cloudfs.protocol.DeleteFSReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:cloudfs.protocol.DeleteFSReq)
      com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_DeleteFSReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_DeleteFSReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq.class, com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq.Builder.class);
    }

    // Construct using com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq.newBuilder()
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
      if (fsPathReqBuilder_ == null) {
        fsPathReq_ = null;
      } else {
        fsPathReq_ = null;
        fsPathReqBuilder_ = null;
      }
      recursive_ = false;

      reqTimeOutInMillis_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_DeleteFSReq_descriptor;
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq getDefaultInstanceForType() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq.getDefaultInstance();
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq build() {
      com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq buildPartial() {
      com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq result = new com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq(this);
      if (fsPathReqBuilder_ == null) {
        result.fsPathReq_ = fsPathReq_;
      } else {
        result.fsPathReq_ = fsPathReqBuilder_.build();
      }
      result.recursive_ = recursive_;
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
      if (other instanceof com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq) {
        return mergeFrom((com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq other) {
      if (other == com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq.getDefaultInstance()) return this;
      if (other.hasFsPathReq()) {
        mergeFsPathReq(other.getFsPathReq());
      }
      if (other.getRecursive() != false) {
        setRecursive(other.getRecursive());
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
      com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq fsPathReq_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder> fsPathReqBuilder_;
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPathReq = 1;</code>
     * @return Whether the fsPathReq field is set.
     */
    public boolean hasFsPathReq() {
      return fsPathReqBuilder_ != null || fsPathReq_ != null;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPathReq = 1;</code>
     * @return The fsPathReq.
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq getFsPathReq() {
      if (fsPathReqBuilder_ == null) {
        return fsPathReq_ == null ? com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.getDefaultInstance() : fsPathReq_;
      } else {
        return fsPathReqBuilder_.getMessage();
      }
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPathReq = 1;</code>
     */
    public Builder setFsPathReq(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq value) {
      if (fsPathReqBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        fsPathReq_ = value;
        onChanged();
      } else {
        fsPathReqBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPathReq = 1;</code>
     */
    public Builder setFsPathReq(
        com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder builderForValue) {
      if (fsPathReqBuilder_ == null) {
        fsPathReq_ = builderForValue.build();
        onChanged();
      } else {
        fsPathReqBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPathReq = 1;</code>
     */
    public Builder mergeFsPathReq(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq value) {
      if (fsPathReqBuilder_ == null) {
        if (fsPathReq_ != null) {
          fsPathReq_ =
            com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.newBuilder(fsPathReq_).mergeFrom(value).buildPartial();
        } else {
          fsPathReq_ = value;
        }
        onChanged();
      } else {
        fsPathReqBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPathReq = 1;</code>
     */
    public Builder clearFsPathReq() {
      if (fsPathReqBuilder_ == null) {
        fsPathReq_ = null;
        onChanged();
      } else {
        fsPathReq_ = null;
        fsPathReqBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPathReq = 1;</code>
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder getFsPathReqBuilder() {
      
      onChanged();
      return getFsPathReqFieldBuilder().getBuilder();
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPathReq = 1;</code>
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder getFsPathReqOrBuilder() {
      if (fsPathReqBuilder_ != null) {
        return fsPathReqBuilder_.getMessageOrBuilder();
      } else {
        return fsPathReq_ == null ?
            com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.getDefaultInstance() : fsPathReq_;
      }
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq fsPathReq = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder> 
        getFsPathReqFieldBuilder() {
      if (fsPathReqBuilder_ == null) {
        fsPathReqBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder>(
                getFsPathReq(),
                getParentForChildren(),
                isClean());
        fsPathReq_ = null;
      }
      return fsPathReqBuilder_;
    }

    private boolean recursive_ ;
    /**
     * <code>bool recursive = 2;</code>
     * @return The recursive.
     */
    public boolean getRecursive() {
      return recursive_;
    }
    /**
     * <code>bool recursive = 2;</code>
     * @param value The recursive to set.
     * @return This builder for chaining.
     */
    public Builder setRecursive(boolean value) {
      
      recursive_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool recursive = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearRecursive() {
      
      recursive_ = false;
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


    // @@protoc_insertion_point(builder_scope:cloudfs.protocol.DeleteFSReq)
  }

  // @@protoc_insertion_point(class_scope:cloudfs.protocol.DeleteFSReq)
  private static final com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq();
  }

  public static com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DeleteFSReq>
      PARSER = new com.google.protobuf.AbstractParser<DeleteFSReq>() {
    @java.lang.Override
    public DeleteFSReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DeleteFSReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DeleteFSReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DeleteFSReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
