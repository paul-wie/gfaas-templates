package main

import (
	"google.golang.org/grpc/reflection"
	"log"
	"net"

	"google.golang.org/grpc"
)

func main() {

	lis, err := net.Listen("tcp", ":8080")
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}

	grpcServer := grpc.NewServer()

	RegisterFunctionServer(grpcServer, &Server{})

	reflection.Register(grpcServer)
	if err := grpcServer.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %s", err)
	}
}
