// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MarketSnapshotPacketSpec.proto

package org.openfeed.proto.data;

public interface MarketSnapshotPacketOrBuilder
    extends com.google.protobuf.MessageOrBuilder {

  // optional sint32 channel = 1;
  /**
   * <code>optional sint32 channel = 1;</code>
   *
   * <pre>
   * Packet channel number.
   * Globally unique in the data feed.
   * </pre>
   */
  boolean hasChannel();
  /**
   * <code>optional sint32 channel = 1;</code>
   *
   * <pre>
   * Packet channel number.
   * Globally unique in the data feed.
   * </pre>
   */
  int getChannel();

  // optional sint64 sequence = 2;
  /**
   * <code>optional sint64 sequence = 2;</code>
   *
   * <pre>
   * Packet sequence number inside the channel.
   * Globally unique in the channel during the trade day.
   * Positive, reset to 1 every trade day.
   * Reset happens on local market date change or market open event.
   * </pre>
   */
  boolean hasSequence();
  /**
   * <code>optional sint64 sequence = 2;</code>
   *
   * <pre>
   * Packet sequence number inside the channel.
   * Globally unique in the channel during the trade day.
   * Positive, reset to 1 every trade day.
   * Reset happens on local market date change or market open event.
   * </pre>
   */
  long getSequence();

  // optional sint64 timeStamp = 3;
  /**
   * <code>optional sint64 timeStamp = 3;</code>
   *
   * <pre>
   * Packet send time.
   * Base for message entries, millisFIX, UTC
   * </pre>
   */
  boolean hasTimeStamp();
  /**
   * <code>optional sint64 timeStamp = 3;</code>
   *
   * <pre>
   * Packet send time.
   * Base for message entries, millisFIX, UTC
   * </pre>
   */
  long getTimeStamp();

  // optional .org.openfeed.proto.data.PacketType type = 4 [default = MARKET_SNAPSHOT];
  /**
   * <code>optional .org.openfeed.proto.data.PacketType type = 4 [default = MARKET_SNAPSHOT];</code>
   *
   * <pre>
   * Packet body type selector.
   * </pre>
   */
  boolean hasType();
  /**
   * <code>optional .org.openfeed.proto.data.PacketType type = 4 [default = MARKET_SNAPSHOT];</code>
   *
   * <pre>
   * Packet body type selector.
   * </pre>
   */
  org.openfeed.proto.data.PacketType getType();

  // repeated .org.openfeed.proto.data.MarketSnapshotMessage message = 5;
  /**
   * <code>repeated .org.openfeed.proto.data.MarketSnapshotMessage message = 5;</code>
   *
   * <pre>
   * Ordered list of snapshot messages.
   * </pre>
   */
  java.util.List<org.openfeed.proto.data.MarketSnapshotMessage> 
      getMessageList();
  /**
   * <code>repeated .org.openfeed.proto.data.MarketSnapshotMessage message = 5;</code>
   *
   * <pre>
   * Ordered list of snapshot messages.
   * </pre>
   */
  org.openfeed.proto.data.MarketSnapshotMessage getMessage(int index);
  /**
   * <code>repeated .org.openfeed.proto.data.MarketSnapshotMessage message = 5;</code>
   *
   * <pre>
   * Ordered list of snapshot messages.
   * </pre>
   */
  int getMessageCount();
  /**
   * <code>repeated .org.openfeed.proto.data.MarketSnapshotMessage message = 5;</code>
   *
   * <pre>
   * Ordered list of snapshot messages.
   * </pre>
   */
  java.util.List<? extends org.openfeed.proto.data.MarketSnapshotMessageOrBuilder> 
      getMessageOrBuilderList();
  /**
   * <code>repeated .org.openfeed.proto.data.MarketSnapshotMessage message = 5;</code>
   *
   * <pre>
   * Ordered list of snapshot messages.
   * </pre>
   */
  org.openfeed.proto.data.MarketSnapshotMessageOrBuilder getMessageOrBuilder(
      int index);
}
