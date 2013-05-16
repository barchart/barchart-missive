// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MarketUpdatePacketSpec.proto

package org.openfeed.proto.data;

public interface MarketUpdatePacketOrBuilder
    extends com.google.protobuf.MessageOrBuilder {

  // optional sint32 channel = 1;
  /**
   * <code>optional sint32 channel = 1;</code>
   *
   * <pre>
   * Packet channel number.
   * Globally unique in data feed.
   * </pre>
   */
  boolean hasChannel();
  /**
   * <code>optional sint32 channel = 1;</code>
   *
   * <pre>
   * Packet channel number.
   * Globally unique in data feed.
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

  // optional .org.openfeed.proto.data.PacketType packetType = 4 [default = MARKET_UPDATE];
  /**
   * <code>optional .org.openfeed.proto.data.PacketType packetType = 4 [default = MARKET_UPDATE];</code>
   *
   * <pre>
   * Packet body type selector.
   * </pre>
   */
  boolean hasPacketType();
  /**
   * <code>optional .org.openfeed.proto.data.PacketType packetType = 4 [default = MARKET_UPDATE];</code>
   *
   * <pre>
   * Packet body type selector.
   * </pre>
   */
  org.openfeed.proto.data.PacketType getPacketType();

  // repeated .org.openfeed.proto.data.MarketUpdateMessage message = 5;
  /**
   * <code>repeated .org.openfeed.proto.data.MarketUpdateMessage message = 5;</code>
   *
   * <pre>
   * Ordered list of update messages.
   * </pre>
   */
  java.util.List<org.openfeed.proto.data.MarketUpdateMessage> 
      getMessageList();
  /**
   * <code>repeated .org.openfeed.proto.data.MarketUpdateMessage message = 5;</code>
   *
   * <pre>
   * Ordered list of update messages.
   * </pre>
   */
  org.openfeed.proto.data.MarketUpdateMessage getMessage(int index);
  /**
   * <code>repeated .org.openfeed.proto.data.MarketUpdateMessage message = 5;</code>
   *
   * <pre>
   * Ordered list of update messages.
   * </pre>
   */
  int getMessageCount();
  /**
   * <code>repeated .org.openfeed.proto.data.MarketUpdateMessage message = 5;</code>
   *
   * <pre>
   * Ordered list of update messages.
   * </pre>
   */
  java.util.List<? extends org.openfeed.proto.data.MarketUpdateMessageOrBuilder> 
      getMessageOrBuilderList();
  /**
   * <code>repeated .org.openfeed.proto.data.MarketUpdateMessage message = 5;</code>
   *
   * <pre>
   * Ordered list of update messages.
   * </pre>
   */
  org.openfeed.proto.data.MarketUpdateMessageOrBuilder getMessageOrBuilder(
      int index);
}
