import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// Q1.4 Driver: Hadoop MapReduce configuration settings
public class Q1_4_Driver {

    public static void main(String[] args) throws Exception {
        // args[0] = input folder, args[1] = output folder, args[2] = keyword
        if (args.length != 3) {
            System.err.println("Usage: Q1_4_Driver <input folder> <output folder> <keyword>");
            System.exit(2);
        }
        Configuration conf = new Configuration();
        conf.set("keyword", args[2]);                          // keyword passed in dynamically

        Job job = Job.getInstance(conf, "keyword location search");
        job.setJarByClass(Q1_4_Driver.class);
        job.setMapperClass(Q1_2_Mapper.class);
        job.setReducerClass(Q1_3_Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileInputFormat.setMinInputSplitSize(job, Long.MAX_VALUE);   // one Mapper per document, so row numbers stay correct
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
