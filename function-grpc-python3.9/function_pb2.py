# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: function.proto
"""Generated protocol buffer code."""
from google.protobuf.internal import builder as _builder
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x0e\x66unction.proto\x12\torg.proto\"\x1f\n\x0f\x46unctionRequest\x12\x0c\n\x04text\x18\x01 \x01(\t\" \n\x10\x46unctionResponse\x12\x0c\n\x04text\x18\x01 \x01(\t2O\n\x08\x46unction\x12\x43\n\x06invoke\x12\x1a.org.proto.FunctionRequest\x1a\x1b.org.proto.FunctionResponse\"\x00\x62\x06proto3')

_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, globals())
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'function_pb2', globals())
if _descriptor._USE_C_DESCRIPTORS == False:

  DESCRIPTOR._options = None
  _FUNCTIONREQUEST._serialized_start=29
  _FUNCTIONREQUEST._serialized_end=60
  _FUNCTIONRESPONSE._serialized_start=62
  _FUNCTIONRESPONSE._serialized_end=94
  _FUNCTION._serialized_start=96
  _FUNCTION._serialized_end=175
# @@protoc_insertion_point(module_scope)
