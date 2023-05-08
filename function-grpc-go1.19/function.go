package main

import (
	"golang.org/x/net/context"
	"log"
)

type Server struct {
}

func (s *Server) mustEmbedUnimplementedFunctionServer() {
	panic("implement me")
}

func (s *Server) Invoke(ctx context.Context, in *FunctionRequest) (*FunctionResponse, error) {
	log.Printf("Receive message body from client: %s", in.Text)
	return &FunctionResponse{Text: "Hello From the Server!"}, nil
}
