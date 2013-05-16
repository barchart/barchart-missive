// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MarketPacketTypeSpec.proto

package org.openfeed.proto.data;

/**
 * Protobuf enum {@code org.openfeed.proto.data.PacketType}
 *
 * <pre>
 * Packet type for market data.
 * </pre>
 */
public enum PacketType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>UNKNOWN = 0;</code>
   *
   * <pre>
   * Invalid type.
   * Represents error condition.
   * </pre>
   */
  UNKNOWN(0, 0),
  /**
   * <code>MARKET_UPDATE = 1;</code>
   *
   * <pre>
   * Market data update packet.
   * Contains only update messages.
   * </pre>
   */
  MARKET_UPDATE(1, 1),
  /**
   * <code>MARKET_SNAPSHOT = 2;</code>
   *
   * <pre>
   * Market data snapshot packet.
   * Contains only snapshot messages.
   * </pre>
   */
  MARKET_SNAPSHOT(2, 2),
  ;

  /**
   * <code>UNKNOWN = 0;</code>
   *
   * <pre>
   * Invalid type.
   * Represents error condition.
   * </pre>
   */
  public static final int UNKNOWN_VALUE = 0;
  /**
   * <code>MARKET_UPDATE = 1;</code>
   *
   * <pre>
   * Market data update packet.
   * Contains only update messages.
   * </pre>
   */
  public static final int MARKET_UPDATE_VALUE = 1;
  /**
   * <code>MARKET_SNAPSHOT = 2;</code>
   *
   * <pre>
   * Market data snapshot packet.
   * Contains only snapshot messages.
   * </pre>
   */
  public static final int MARKET_SNAPSHOT_VALUE = 2;


  public final int getNumber() { return value; }

  public static PacketType valueOf(int value) {
    switch (value) {
      case 0: return UNKNOWN;
      case 1: return MARKET_UPDATE;
      case 2: return MARKET_SNAPSHOT;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<PacketType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static com.google.protobuf.Internal.EnumLiteMap<PacketType>
      internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<PacketType>() {
          public PacketType findValueByNumber(int number) {
            return PacketType.valueOf(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(index);
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return org.openfeed.proto.data.MarketPacketTypeSpec.getDescriptor().getEnumTypes().get(0);
  }

  private static final PacketType[] VALUES = values();

  public static PacketType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    return VALUES[desc.getIndex()];
  }

  private final int index;
  private final int value;

  private PacketType(int index, int value) {
    this.index = index;
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:org.openfeed.proto.data.PacketType)
}
