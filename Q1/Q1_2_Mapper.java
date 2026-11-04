import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

// Q1.2 Mapper: finds the keyword in every line of a document and emits <document, row:column>
public class Q1_2_Mapper extends Mapper<Object, Text, Text, Text> {

    private String keyword;
    private Text fileName = new Text();
    private int row = 0;

    @Override
    protected void setup(Context context) {
        // the keyword comes from the job configuration, so it is not hard-coded
        keyword = context.getConfiguration().get("keyword").toLowerCase();
        // name of the document this Mapper is reading
        fileName.set(((FileSplit) context.getInputSplit()).getPath().getName());
    }

    @Override
    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        row++;                                                 // current line (row)
        String line = value.toString().toLowerCase().replaceAll("(?U)^\\W+", "");
        String[] words = line.split("(?U)\\W+");               // words in the line

        for (int col = 0; col < words.length; col++) {
            if (words[col].equals(keyword)) {
                context.write(fileName, new Text(row + ":" + (col + 1)));   // <document, row:column>
            }
        }
    }
}
