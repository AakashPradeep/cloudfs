// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cloudfs.proto

package com.aakash.cloudfs.protocol.proto.generated.stubs;

/**
 * Protobuf type {@code cloudfs.protocol.CreateFileReq}
 */
public  final class CreateFileReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:cloudfs.protocol.CreateFileReq)
    CreateFileReqOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CreateFileReq.newBuilder() to construct.
  private CreateFileReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CreateFileReq() {
    vendorPath_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new CreateFileReq();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CreateFileReq(
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

            replication_ = input.readInt32();
            break;
          }
          case 32: {

            blockSize_ = input.readInt64();
            break;
          }
          case 40: {

            fileSize_ = input.readInt64();
            break;
          }
          case 50: {
            java.lang.String s = input.readStringRequireUtf8();

            vendorPath_ = s;
            break;
          }
          case 56: {

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
    return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_CreateFileReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_CreateFileReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq.class, com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq.Builder.class);
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

  public static final int REPLICATION_FIELD_NUMBER = 3;
  private int replication_;
  /**
   * <code>int32 replication = 3;</code>
   * @return The replication.
   */
  public int getReplication() {
    return replication_;
  }

  public static final int BLOCKSIZE_FIELD_NUMBER = 4;
  private long blockSize_;
  /**
   * <code>int64 blockSize = 4;</code>
   * @return The blockSize.
   */
  public long getBlockSize() {
    return blockSize_;
  }

  public static final int FILESIZE_FIELD_NUMBER = 5;
  private long fileSize_;
  /**
   * <code>int64 fileSize = 5;</code>
   * @return The fileSize.
   */
  public long getFileSize() {
    return fileSize_;
  }

  public static final int VENDORPATH_FIELD_NUMBER = 6;
  private volatile java.lang.Object vendorPath_;
  /**
   * <code>string vendorPath = 6;</code>
   * @return The vendorPath.
   */
  public java.lang.String getVendorPath() {
    java.lang.Object ref = vendorPath_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      vendorPath_ = s;
      return s;
    }
  }
  /**
   * <code>string vendorPath = 6;</code>
   * @return The bytes for vendorPath.
   */
  public com.google.protobuf.ByteString
      getVendorPathBytes() {
    java.lang.Object ref = vendorPath_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      vendorPath_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int REQTIMEOUTINMILLIS_FIELD_NUMBER = 7;
  private long reqTimeOutInMillis_;
  /**
   * <code>int64 reqTimeOutInMillis = 7;</code>
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
    if (replication_ != 0) {
      output.writeInt32(3, replication_);
    }
    if (blockSize_ != 0L) {
      output.writeInt64(4, blockSize_);
    }
    if (fileSize_ != 0L) {
      output.writeInt64(5, fileSize_);
    }
    if (!getVendorPathBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, vendorPath_);
    }
    if (reqTimeOutInMillis_ != 0L) {
      output.writeInt64(7, reqTimeOutInMillis_);
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
    if (replication_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, replication_);
    }
    if (blockSize_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, blockSize_);
    }
    if (fileSize_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(5, fileSize_);
    }
    if (!getVendorPathBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, vendorPath_);
    }
    if (reqTimeOutInMillis_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(7, reqTimeOutInMillis_);
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
    if (!(obj instanceof com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq)) {
      return super.equals(obj);
    }
    com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq other = (com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq) obj;

    if (hasFsPath() != other.hasFsPath()) return false;
    if (hasFsPath()) {
      if (!getFsPath()
          .equals(other.getFsPath())) return false;
    }
    if (getPermission()
        != other.getPermission()) return false;
    if (getReplication()
        != other.getReplication()) return false;
    if (getBlockSize()
        != other.getBlockSize()) return false;
    if (getFileSize()
        != other.getFileSize()) return false;
    if (!getVendorPath()
        .equals(other.getVendorPath())) return false;
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
    hash = (37 * hash) + REPLICATION_FIELD_NUMBER;
    hash = (53 * hash) + getReplication();
    hash = (37 * hash) + BLOCKSIZE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getBlockSize());
    hash = (37 * hash) + FILESIZE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getFileSize());
    hash = (37 * hash) + VENDORPATH_FIELD_NUMBER;
    hash = (53 * hash) + getVendorPath().hashCode();
    hash = (37 * hash) + REQTIMEOUTINMILLIS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getReqTimeOutInMillis());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq parseFrom(
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
  public static Builder newBuilder(com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq prototype) {
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
   * Protobuf type {@code cloudfs.protocol.CreateFileReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:cloudfs.protocol.CreateFileReq)
      com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_CreateFileReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_CreateFileReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq.class, com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq.Builder.class);
    }

    // Construct using com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq.newBuilder()
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

      replication_ = 0;

      blockSize_ = 0L;

      fileSize_ = 0L;

      vendorPath_ = "";

      reqTimeOutInMillis_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.internal_static_cloudfs_protocol_CreateFileReq_descriptor;
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq getDefaultInstanceForType() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq.getDefaultInstance();
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq build() {
      com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq buildPartial() {
      com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq result = new com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq(this);
      if (fsPathBuilder_ == null) {
        result.fsPath_ = fsPath_;
      } else {
        result.fsPath_ = fsPathBuilder_.build();
      }
      result.permission_ = permission_;
      result.replication_ = replication_;
      result.blockSize_ = blockSize_;
      result.fileSize_ = fileSize_;
      result.vendorPath_ = vendorPath_;
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
      if (other instanceof com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq) {
        return mergeFrom((com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq other) {
      if (other == com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq.getDefaultInstance()) return this;
      if (other.hasFsPath()) {
        mergeFsPath(other.getFsPath());
      }
      if (other.getPermission() != 0) {
        setPermission(other.getPermission());
      }
      if (other.getReplication() != 0) {
        setReplication(other.getReplication());
      }
      if (other.getBlockSize() != 0L) {
        setBlockSize(other.getBlockSize());
      }
      if (other.getFileSize() != 0L) {
        setFileSize(other.getFileSize());
      }
      if (!other.getVendorPath().isEmpty()) {
        vendorPath_ = other.vendorPath_;
        onChanged();
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
      com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq) e.getUnfinishedMessage();
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

    private int replication_ ;
    /**
     * <code>int32 replication = 3;</code>
     * @return The replication.
     */
    public int getReplication() {
      return replication_;
    }
    /**
     * <code>int32 replication = 3;</code>
     * @param value The replication to set.
     * @return This builder for chaining.
     */
    public Builder setReplication(int value) {
      
      replication_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 replication = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearReplication() {
      
      replication_ = 0;
      onChanged();
      return this;
    }

    private long blockSize_ ;
    /**
     * <code>int64 blockSize = 4;</code>
     * @return The blockSize.
     */
    public long getBlockSize() {
      return blockSize_;
    }
    /**
     * <code>int64 blockSize = 4;</code>
     * @param value The blockSize to set.
     * @return This builder for chaining.
     */
    public Builder setBlockSize(long value) {
      
      blockSize_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 blockSize = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearBlockSize() {
      
      blockSize_ = 0L;
      onChanged();
      return this;
    }

    private long fileSize_ ;
    /**
     * <code>int64 fileSize = 5;</code>
     * @return The fileSize.
     */
    public long getFileSize() {
      return fileSize_;
    }
    /**
     * <code>int64 fileSize = 5;</code>
     * @param value The fileSize to set.
     * @return This builder for chaining.
     */
    public Builder setFileSize(long value) {
      
      fileSize_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 fileSize = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearFileSize() {
      
      fileSize_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object vendorPath_ = "";
    /**
     * <code>string vendorPath = 6;</code>
     * @return The vendorPath.
     */
    public java.lang.String getVendorPath() {
      java.lang.Object ref = vendorPath_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        vendorPath_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string vendorPath = 6;</code>
     * @return The bytes for vendorPath.
     */
    public com.google.protobuf.ByteString
        getVendorPathBytes() {
      java.lang.Object ref = vendorPath_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        vendorPath_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string vendorPath = 6;</code>
     * @param value The vendorPath to set.
     * @return This builder for chaining.
     */
    public Builder setVendorPath(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      vendorPath_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string vendorPath = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearVendorPath() {
      
      vendorPath_ = getDefaultInstance().getVendorPath();
      onChanged();
      return this;
    }
    /**
     * <code>string vendorPath = 6;</code>
     * @param value The bytes for vendorPath to set.
     * @return This builder for chaining.
     */
    public Builder setVendorPathBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      vendorPath_ = value;
      onChanged();
      return this;
    }

    private long reqTimeOutInMillis_ ;
    /**
     * <code>int64 reqTimeOutInMillis = 7;</code>
     * @return The reqTimeOutInMillis.
     */
    public long getReqTimeOutInMillis() {
      return reqTimeOutInMillis_;
    }
    /**
     * <code>int64 reqTimeOutInMillis = 7;</code>
     * @param value The reqTimeOutInMillis to set.
     * @return This builder for chaining.
     */
    public Builder setReqTimeOutInMillis(long value) {
      
      reqTimeOutInMillis_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 reqTimeOutInMillis = 7;</code>
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


    // @@protoc_insertion_point(builder_scope:cloudfs.protocol.CreateFileReq)
  }

  // @@protoc_insertion_point(class_scope:cloudfs.protocol.CreateFileReq)
  private static final com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq();
  }

  public static com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CreateFileReq>
      PARSER = new com.google.protobuf.AbstractParser<CreateFileReq>() {
    @java.lang.Override
    public CreateFileReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CreateFileReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CreateFileReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CreateFileReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
