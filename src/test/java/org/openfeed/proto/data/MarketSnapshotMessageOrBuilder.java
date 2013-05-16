// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MarketSnapshotMessageSpec.proto

package org.openfeed.proto.data;

public interface MarketSnapshotMessageOrBuilder
    extends com.google.protobuf.MessageOrBuilder {

  // optional sint64 marketId = 1;
  /**
   * <code>optional sint64 marketId = 1;</code>
   *
   * <pre>
   * Market GUID.
   * Base for entries.
   * </pre>
   */
  boolean hasMarketId();
  /**
   * <code>optional sint64 marketId = 1;</code>
   *
   * <pre>
   * Market GUID.
   * Base for entries.
   * </pre>
   */
  long getMarketId();

  // optional sint64 sequence = 2;
  /**
   * <code>optional sint64 sequence = 2;</code>
   */
  boolean hasSequence();
  /**
   * <code>optional sint64 sequence = 2;</code>
   */
  long getSequence();

  // optional sint64 lastUpdateSequence = 3;
  /**
   * <code>optional sint64 lastUpdateSequence = 3;</code>
   *
   * <pre>
   * Last update sequence number included in snapshot
   * </pre>
   */
  boolean hasLastUpdateSequence();
  /**
   * <code>optional sint64 lastUpdateSequence = 3;</code>
   *
   * <pre>
   * Last update sequence number included in snapshot
   * </pre>
   */
  long getLastUpdateSequence();

  // optional sint64 timeStamp = 4;
  /**
   * <code>optional sint64 timeStamp = 4;</code>
   *
   * <pre>
   * Message/Entry send time.
   * Base for entries, millisFIX, UTC
   * Date/Time in format 2012-12-31T23:59:59.123 UTC -&gt; 20121231235959123
   * </pre>
   */
  boolean hasTimeStamp();
  /**
   * <code>optional sint64 timeStamp = 4;</code>
   *
   * <pre>
   * Message/Entry send time.
   * Base for entries, millisFIX, UTC
   * Date/Time in format 2012-12-31T23:59:59.123 UTC -&gt; 20121231235959123
   * </pre>
   */
  long getTimeStamp();

  // optional sint32 tradeDate = 5;
  /**
   * <code>optional sint32 tradeDate = 5;</code>
   *
   * <pre>
   * Message/Entry trade date.
   * Base for entries, days, market local time zone.
   * Date/Only in format 2012-07-04 -&gt; 20120704
   * </pre>
   */
  boolean hasTradeDate();
  /**
   * <code>optional sint32 tradeDate = 5;</code>
   *
   * <pre>
   * Message/Entry trade date.
   * Base for entries, days, market local time zone.
   * Date/Only in format 2012-07-04 -&gt; 20120704
   * </pre>
   */
  int getTradeDate();

  // repeated .org.openfeed.proto.data.MarketEntry entry = 6;
  /**
   * <code>repeated .org.openfeed.proto.data.MarketEntry entry = 6;</code>
   *
   * <pre>
   * Ordered list of market entries.
   * </pre>
   */
  java.util.List<org.openfeed.proto.data.MarketEntry> 
      getEntryList();
  /**
   * <code>repeated .org.openfeed.proto.data.MarketEntry entry = 6;</code>
   *
   * <pre>
   * Ordered list of market entries.
   * </pre>
   */
  org.openfeed.proto.data.MarketEntry getEntry(int index);
  /**
   * <code>repeated .org.openfeed.proto.data.MarketEntry entry = 6;</code>
   *
   * <pre>
   * Ordered list of market entries.
   * </pre>
   */
  int getEntryCount();
  /**
   * <code>repeated .org.openfeed.proto.data.MarketEntry entry = 6;</code>
   *
   * <pre>
   * Ordered list of market entries.
   * </pre>
   */
  java.util.List<? extends org.openfeed.proto.data.MarketEntryOrBuilder> 
      getEntryOrBuilderList();
  /**
   * <code>repeated .org.openfeed.proto.data.MarketEntry entry = 6;</code>
   *
   * <pre>
   * Ordered list of market entries.
   * </pre>
   */
  org.openfeed.proto.data.MarketEntryOrBuilder getEntryOrBuilder(
      int index);

  // optional sint32 priceExponent = 7;
  /**
   * <code>optional sint32 priceExponent = 7;</code>
   *
   * <pre>
   * Shared base price exponent for market entries.
   * </pre>
   */
  boolean hasPriceExponent();
  /**
   * <code>optional sint32 priceExponent = 7;</code>
   *
   * <pre>
   * Shared base price exponent for market entries.
   * </pre>
   */
  int getPriceExponent();

  // optional sint32 sizeExponent = 8;
  /**
   * <code>optional sint32 sizeExponent = 8;</code>
   *
   * <pre>
   * Shared base size exponent for market entries.
   * </pre>
   */
  boolean hasSizeExponent();
  /**
   * <code>optional sint32 sizeExponent = 8;</code>
   *
   * <pre>
   * Shared base size exponent for market entries.
   * </pre>
   */
  int getSizeExponent();

  // optional sint32 totalExpectedEntries = 9;
  /**
   * <code>optional sint32 totalExpectedEntries = 9;</code>
   *
   * <pre>
   * Total number of MarketEntry items to expect to receive
   * before the snapshot for this marketId can be considered complete.
   * Entries for a given marketId may come in multiple packets and messages.
   * Each snapshot packet/message must contain only entries for a single marketId.
   * </pre>
   */
  boolean hasTotalExpectedEntries();
  /**
   * <code>optional sint32 totalExpectedEntries = 9;</code>
   *
   * <pre>
   * Total number of MarketEntry items to expect to receive
   * before the snapshot for this marketId can be considered complete.
   * Entries for a given marketId may come in multiple packets and messages.
   * Each snapshot packet/message must contain only entries for a single marketId.
   * </pre>
   */
  int getTotalExpectedEntries();
}