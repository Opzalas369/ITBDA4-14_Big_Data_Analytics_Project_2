import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// Q1.3 Reducer: receives one document with all its row:column locations and writes one line
public class Q1_3_Reducer extends Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        List<String> locations = new ArrayList<String>();
        for (Text value : values) {                    // every row:column found in this document
            locations.add(value.toString());
        }

        // Hadoop does not keep the values in the order they were found, so sort by row then column
        Collections.sort(locations, (a, b) -> {
            String[] x = a.split(":");
            String[] y = b.split(":");
            int byRow = Integer.compare(Integer.parseInt(x[0]), Integer.parseInt(y[0]));
            return byRow != 0 ? byRow : Integer.compare(Integer.parseInt(x[1]), Integer.parseInt(y[1]));
        });

        context.write(key, new Text(locations.size() + " match(es) at " + String.join(" ", locations)));
    }
}
