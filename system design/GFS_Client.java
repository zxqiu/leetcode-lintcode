/*

GFS Client


Implement a simple client for GFS (Google File System, a distributed file system), it provides the following methods:

    read(filename). Read the file with given filename from GFS.
    write(filename, content). Write a file with given filename & content to GFS.

There are two private methods that already implemented in the base class:

    readChunk(filename, chunkIndex). Read a chunk from GFS.
    writeChunk(filename, chunkIndex, chunkData). Write a chunk to GFS.

To simplify this question, we can assume that the chunk size is chunkSize bytes. (In a real world system, it is 64M). The GFS Client's job is splitting a file into multiple chunks (if need) and save to the remote GFS server. chunkSize will be given in the constructor. You need to call these two private methods to implement read & write methods.


 Example

GFSClient(5)
read("a.txt")
>> null
write("a.txt", "World")
>> You don't need to return anything, but you need to call writeChunk("a.txt", 0, "World") to write a 5 bytes chunk to GFS.
read("a.txt")
>> "World"
write("b.txt", "111112222233")
>> You need to save "11111" at chink 0, "22222" at chunk 1, "33" at chunk 2.
write("b.txt", "aaaaabbbbb")
read("b.txt")
>> "aaaaabbbbb"


解：
由于没有提供Master Server，需要Client自己记录每个文件名对应的所有Chunk Server。
分析Example，发现Chunk Server Index只需要将文件按照chunkSize切块，然后Index从0到块数减1就可以了。
如此，只需要HashMap只需要记录切了几块，然后从第0块写到最后一块，读的时候也从0块读到最后一块即可。

*/


/* Definition of BaseGFSClient
 * class BaseGFSClient {
 *     private Map<String, String> chunk_list;
 *     public BaseGFSClient() {}
 *     public String readChunk(String filename, int chunkIndex) {
 *         // Read a chunk from GFS
 *     }
 *     public void writeChunk(String filename, int chunkIndex,
 *                            String content) {
 *         // Write a chunk to GFS
 *     }
 * }
 */
public class GFSClient extends BaseGFSClient {
    Map<String, Integer> name2Index;
    private int chunkSize;

    public GFSClient(int chunkSize) {
        this.chunkSize = chunkSize;
        name2Index = new HashMap<>();
    }
    
    // @param filename a file name
    // @return conetent of the file given from GFS
    public String read(String filename) {
        if (!name2Index.containsKey(filename)) {
            return null;
        }
        
        String ret = "";
        for (int i = 0; i < name2Index.get(filename); i++) {
            ret += readChunk(filename, i);
        }
        
        return ret;
        
    }

    // @param filename a file name
    // @param content a string
    // @return void
    public void write(String filename, String content) {
        int chunkNum = content.length() / chunkSize;
        chunkNum += (content.length() % chunkSize == 0) ? 0 : 1;
        
        name2Index.put(filename, chunkNum);
        
        for (int i = 0; i < chunkNum; i++) {
            int start = i * chunkSize;
            int end = Math.min(content.length(), (i + 1) * chunkSize);
            writeChunk(filename, i, content.substring(start, end));
        }
    }
}
