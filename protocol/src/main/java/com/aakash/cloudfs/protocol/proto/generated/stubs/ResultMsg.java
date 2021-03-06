// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cloudfs.proto

package com.aakash.cloudfs.protocol.proto.generated.stubs;

/**
 * Protobuf type {@code cloudfs.protocol.ResultMsg}
 */
public  final class ResultMsg extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:cloudfs.protocol.ResultMsg)
    ResultMsgOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ResultMsg.newBuilder() to construct.
  private ResultMsg(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ResultMsg() {
    status_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ResultMsg();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ResultMsg(
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
            if (cfsPath_ != null) {
              subBuilder = cfsPath_.toBuilder();
            }
            cfsPath_ = input.readMessage(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(cfsPath_);
              cfsPath_ = subBuilder.buildPartial();
            }

            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            status_ = rawValue;
            break;
          }
          case 24: {

            success_ = input.readBool();
            break;
          }
          case 34: {
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
    return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_ResultMsg_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_ResultMsg_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.class, com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.Builder.class);
  }

  public static final int CFSPATH_FIELD_NUMBER = 1;
  private com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq cfsPath_;
  /**
   * <code>.cloudfs.protocol.FSPathReq cfsPath = 1;</code>
   * @return Whether the cfsPath field is set.
   */
  public boolean hasCfsPath() {
    return cfsPath_ != null;
  }
  /**
   * <code>.cloudfs.protocol.FSPathReq cfsPath = 1;</code>
   * @return The cfsPath.
   */
  public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq getCfsPath() {
    return cfsPath_ == null ? com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.getDefaultInstance() : cfsPath_;
  }
  /**
   * <code>.cloudfs.protocol.FSPathReq cfsPath = 1;</code>
   */
  public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder getCfsPathOrBuilder() {
    return getCfsPath();
  }

  public static final int STATUS_FIELD_NUMBER = 2;
  private int status_;
  /**
   * <code>.cloudfs.protocol.Status status = 2;</code>
   * @return The enum numeric value on the wire for status.
   */
  public int getStatusValue() {
    return status_;
  }
  /**
   * <code>.cloudfs.protocol.Status status = 2;</code>
   * @return The status.
   */
  public com.aakash.cloudfs.protocol.proto.generated.stubs.Status getStatus() {
    @SuppressWarnings("deprecation")
    com.aakash.cloudfs.protocol.proto.generated.stubs.Status result = com.aakash.cloudfs.protocol.proto.generated.stubs.Status.valueOf(status_);
    return result == null ? com.aakash.cloudfs.protocol.proto.generated.stubs.Status.UNRECOGNIZED : result;
  }

  public static final int SUCCESS_FIELD_NUMBER = 3;
  private boolean success_;
  /**
   * <code>bool success = 3;</code>
   * @return The success.
   */
  public boolean getSuccess() {
    return success_;
  }

  public static final int ERRORMSG_FIELD_NUMBER = 4;
  private com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg errorMsg_;
  /**
   * <code>.cloudfs.protocol.ErrorMsg errorMsg = 4;</code>
   * @return Whether the errorMsg field is set.
   */
  public boolean hasErrorMsg() {
    return errorMsg_ != null;
  }
  /**
   * <code>.cloudfs.protocol.ErrorMsg errorMsg = 4;</code>
   * @return The errorMsg.
   */
  public com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg getErrorMsg() {
    return errorMsg_ == null ? com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg.getDefaultInstance() : errorMsg_;
  }
  /**
   * <code>.cloudfs.protocol.ErrorMsg errorMsg = 4;</code>
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
    if (cfsPath_ != null) {
      output.writeMessage(1, getCfsPath());
    }
    if (status_ != com.aakash.cloudfs.protocol.proto.generated.stubs.Status.UNDER_WRITING.getNumber()) {
      output.writeEnum(2, status_);
    }
    if (success_ != false) {
      output.writeBool(3, success_);
    }
    if (errorMsg_ != null) {
      output.writeMessage(4, getErrorMsg());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (cfsPath_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getCfsPath());
    }
    if (status_ != com.aakash.cloudfs.protocol.proto.generated.stubs.Status.UNDER_WRITING.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, status_);
    }
    if (success_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(3, success_);
    }
    if (errorMsg_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getErrorMsg());
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
    if (!(obj instanceof com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg)) {
      return super.equals(obj);
    }
    com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg other = (com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg) obj;

    if (hasCfsPath() != other.hasCfsPath()) return false;
    if (hasCfsPath()) {
      if (!getCfsPath()
          .equals(other.getCfsPath())) return false;
    }
    if (status_ != other.status_) return false;
    if (getSuccess()
        != other.getSuccess()) return false;
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
    if (hasCfsPath()) {
      hash = (37 * hash) + CFSPATH_FIELD_NUMBER;
      hash = (53 * hash) + getCfsPath().hashCode();
    }
    hash = (37 * hash) + STATUS_FIELD_NUMBER;
    hash = (53 * hash) + status_;
    hash = (37 * hash) + SUCCESS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getSuccess());
    if (hasErrorMsg()) {
      hash = (37 * hash) + ERRORMSG_FIELD_NUMBER;
      hash = (53 * hash) + getErrorMsg().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg parseFrom(
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
  public static Builder newBuilder(com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg prototype) {
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
   * Protobuf type {@code cloudfs.protocol.ResultMsg}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:cloudfs.protocol.ResultMsg)
      com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsgOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_ResultMsg_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_ResultMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.class, com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.Builder.class);
    }

    // Construct using com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.newBuilder()
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
      if (cfsPathBuilder_ == null) {
        cfsPath_ = null;
      } else {
        cfsPath_ = null;
        cfsPathBuilder_ = null;
      }
      status_ = 0;

      success_ = false;

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
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_ResultMsg_descriptor;
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg getDefaultInstanceForType() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.getDefaultInstance();
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg build() {
      com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg buildPartial() {
      com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg result = new com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg(this);
      if (cfsPathBuilder_ == null) {
        result.cfsPath_ = cfsPath_;
      } else {
        result.cfsPath_ = cfsPathBuilder_.build();
      }
      result.status_ = status_;
      result.success_ = success_;
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
      if (other instanceof com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg) {
        return mergeFrom((com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg other) {
      if (other == com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.getDefaultInstance()) return this;
      if (other.hasCfsPath()) {
        mergeCfsPath(other.getCfsPath());
      }
      if (other.status_ != 0) {
        setStatusValue(other.getStatusValue());
      }
      if (other.getSuccess() != false) {
        setSuccess(other.getSuccess());
      }
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
      com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq cfsPath_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder> cfsPathBuilder_;
    /**
     * <code>.cloudfs.protocol.FSPathReq cfsPath = 1;</code>
     * @return Whether the cfsPath field is set.
     */
    public boolean hasCfsPath() {
      return cfsPathBuilder_ != null || cfsPath_ != null;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq cfsPath = 1;</code>
     * @return The cfsPath.
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq getCfsPath() {
      if (cfsPathBuilder_ == null) {
        return cfsPath_ == null ? com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.getDefaultInstance() : cfsPath_;
      } else {
        return cfsPathBuilder_.getMessage();
      }
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq cfsPath = 1;</code>
     */
    public Builder setCfsPath(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq value) {
      if (cfsPathBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        cfsPath_ = value;
        onChanged();
      } else {
        cfsPathBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq cfsPath = 1;</code>
     */
    public Builder setCfsPath(
        com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder builderForValue) {
      if (cfsPathBuilder_ == null) {
        cfsPath_ = builderForValue.build();
        onChanged();
      } else {
        cfsPathBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq cfsPath = 1;</code>
     */
    public Builder mergeCfsPath(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq value) {
      if (cfsPathBuilder_ == null) {
        if (cfsPath_ != null) {
          cfsPath_ =
            com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.newBuilder(cfsPath_).mergeFrom(value).buildPartial();
        } else {
          cfsPath_ = value;
        }
        onChanged();
      } else {
        cfsPathBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq cfsPath = 1;</code>
     */
    public Builder clearCfsPath() {
      if (cfsPathBuilder_ == null) {
        cfsPath_ = null;
        onChanged();
      } else {
        cfsPath_ = null;
        cfsPathBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq cfsPath = 1;</code>
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder getCfsPathBuilder() {
      
      onChanged();
      return getCfsPathFieldBuilder().getBuilder();
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq cfsPath = 1;</code>
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder getCfsPathOrBuilder() {
      if (cfsPathBuilder_ != null) {
        return cfsPathBuilder_.getMessageOrBuilder();
      } else {
        return cfsPath_ == null ?
            com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.getDefaultInstance() : cfsPath_;
      }
    }
    /**
     * <code>.cloudfs.protocol.FSPathReq cfsPath = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder> 
        getCfsPathFieldBuilder() {
      if (cfsPathBuilder_ == null) {
        cfsPathBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.Builder, com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder>(
                getCfsPath(),
                getParentForChildren(),
                isClean());
        cfsPath_ = null;
      }
      return cfsPathBuilder_;
    }

    private int status_ = 0;
    /**
     * <code>.cloudfs.protocol.Status status = 2;</code>
     * @return The enum numeric value on the wire for status.
     */
    public int getStatusValue() {
      return status_;
    }
    /**
     * <code>.cloudfs.protocol.Status status = 2;</code>
     * @param value The enum numeric value on the wire for status to set.
     * @return This builder for chaining.
     */
    public Builder setStatusValue(int value) {
      status_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.cloudfs.protocol.Status status = 2;</code>
     * @return The status.
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.Status getStatus() {
      @SuppressWarnings("deprecation")
      com.aakash.cloudfs.protocol.proto.generated.stubs.Status result = com.aakash.cloudfs.protocol.proto.generated.stubs.Status.valueOf(status_);
      return result == null ? com.aakash.cloudfs.protocol.proto.generated.stubs.Status.UNRECOGNIZED : result;
    }
    /**
     * <code>.cloudfs.protocol.Status status = 2;</code>
     * @param value The status to set.
     * @return This builder for chaining.
     */
    public Builder setStatus(com.aakash.cloudfs.protocol.proto.generated.stubs.Status value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      status_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.cloudfs.protocol.Status status = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearStatus() {
      
      status_ = 0;
      onChanged();
      return this;
    }

    private boolean success_ ;
    /**
     * <code>bool success = 3;</code>
     * @return The success.
     */
    public boolean getSuccess() {
      return success_;
    }
    /**
     * <code>bool success = 3;</code>
     * @param value The success to set.
     * @return This builder for chaining.
     */
    public Builder setSuccess(boolean value) {
      
      success_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool success = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearSuccess() {
      
      success_ = false;
      onChanged();
      return this;
    }

    private com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg errorMsg_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg, com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg.Builder, com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsgOrBuilder> errorMsgBuilder_;
    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 4;</code>
     * @return Whether the errorMsg field is set.
     */
    public boolean hasErrorMsg() {
      return errorMsgBuilder_ != null || errorMsg_ != null;
    }
    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 4;</code>
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
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 4;</code>
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
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 4;</code>
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
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 4;</code>
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
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 4;</code>
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
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 4;</code>
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg.Builder getErrorMsgBuilder() {
      
      onChanged();
      return getErrorMsgFieldBuilder().getBuilder();
    }
    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 4;</code>
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
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 4;</code>
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


    // @@protoc_insertion_point(builder_scope:cloudfs.protocol.ResultMsg)
  }

  // @@protoc_insertion_point(class_scope:cloudfs.protocol.ResultMsg)
  private static final com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg();
  }

  public static com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ResultMsg>
      PARSER = new com.google.protobuf.AbstractParser<ResultMsg>() {
    @java.lang.Override
    public ResultMsg parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ResultMsg(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ResultMsg> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ResultMsg> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

