package main

//func main() {
//
//	var conn *grpc.ClientConn
//	conn, err := grpc.Dial(":8080", grpc.WithInsecure())
//	if err != nil {
//		log.Fatalf("did not connect: %s", err)
//	}
//	defer conn.Close()
//
//	c := NewFunctionClient(conn)
//
//	response, err := c.Invoke(context.Background(), &FunctionRequest{Text: "Hello From Client!"})
//	if err != nil {
//		log.Fatalf("Error when calling SayHello: %s", err)
//	}
//	log.Printf("Response from server: %s", response.Text)
//
//}
