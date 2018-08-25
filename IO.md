### IO, NIO, AIO

#### IO
  
IO 是面向流的，阻塞的IO，阻塞意味着当一个线程调用read() 或 write()时被阻塞，直到有一些数据被获取，或数据完全写入，线程在此期间不能
干其他事。下面有一个Socket client和server程序。
```java 
// client 
while (true) {
                System.out.println("client input");
                String str = scanner.nextLine();
                pw.println(str);
                pw.flush();
                String read = br.readLine();
                System.out.println("client output" + read);
                if (str.equals("exit")) {
                    break;
                }
            }
// server 
  while (true) {
                String str = br.readLine();
                System.out.println("server read " + str);
                System.out.println("server require input");
                String write = scanner.nextLine();
                pw.println(write);
                pw.flush();
                if (write.equals("exit")) {
                    break;
                }
            }
```
client在收到server发的消息之前，会一直在br.readLine()阻塞;server在收到client发的消息之前也会在br.readLine();阻塞。此时
client和server的主程序阻塞，方法会一直等到数据到来时返回，CPU将执行其他任务。

### NIO
NIO 是面向缓冲区的，非阻塞模式时，如读数据，没数据时，就什么就不会取，而不是保持线程阻塞，直到数据变的可取之前，该线程
可以继续去做其他事情。线程通常将非阻塞IO空闲时间用于其他通道IO操作，所以一个单独的线程可以多个输入和输出。
#### Buffer

![](picture/buffers-modes.png)
- capacity buffer 内存快的大小
- position 当前的位置，最大为capacity - 1
- limit  最大走到的位置
- flip() buffer从写模式切换到读模式，limit -> position,  position -> 0
- clear()  从读模式切换到写模式， position -> 0,  limit -> capacity
#### Selectors
选择器允许一个单独的线程来监视多个输入通道，可以使用一个选择器注册多个通道，然后使用一个线程
来选择通道，这种选择机制可以使一个线程管理多个通道。通道上可以注册以下事件
- SelectionKey.OP_ACCEPT 服务端接受客户端的连接事件
- SelectionKey.OP_CONNECT 客户端连接服务器事件
- SelectionKey.OP_READ 读事件
- SelectionKey.OP_WRITE 写事件


