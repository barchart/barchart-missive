// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MarketUpdatePacketSpec.proto

package org.openfeed.proto.data;

public final class MarketUpdatePacketSpec {
  private MarketUpdatePacketSpec() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_org_openfeed_proto_data_MarketUpdatePacket_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_org_openfeed_proto_data_MarketUpdatePacket_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\034MarketUpdatePacketSpec.proto\022\027org.open" +
      "feed.proto.data\032\032MarketPacketTypeSpec.pr" +
      "oto\032\035MarketUpdateMessageSpec.proto\"\321\001\n\022M" +
      "arketUpdatePacket\022\017\n\007channel\030\001 \001(\021\022\020\n\010se" +
      "quence\030\002 \001(\022\022\021\n\ttimeStamp\030\003 \001(\022\022F\n\npacke" +
      "tType\030\004 \001(\0162#.org.openfeed.proto.data.Pa" +
      "cketType:\rMARKET_UPDATE\022=\n\007message\030\005 \003(\013" +
      "2,.org.openfeed.proto.data.MarketUpdateM" +
      "essageB\007H\001P\001\240\001\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_org_openfeed_proto_data_MarketUpdatePacket_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_org_openfeed_proto_data_MarketUpdatePacket_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_org_openfeed_proto_data_MarketUpdatePacket_descriptor,
              new java.lang.String[] { "Channel", "Sequence", "TimeStamp", "PacketType", "Message", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          org.openfeed.proto.data.MarketPacketTypeSpec.getDescriptor(),
          org.openfeed.proto.data.MarketUpdateMessageSpec.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
