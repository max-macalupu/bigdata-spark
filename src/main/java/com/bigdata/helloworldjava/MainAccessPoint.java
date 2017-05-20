package com.bigdata.helloworldjava;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by mx on 20/5/2017.
 */
public class MainAccessPoint {

    public static void main(String[] args) {
        if(args.length==0){
            System.out.println("Error because the parameters are emtpy");
            System.exit(0);
        }
        setupEnviromentVariables(Paths.get(args[0]));
        processWordCount_Java8(Paths.get("classes/README.md"));
    }

    private static void setupEnviromentVariables(Path path){
        System.setProperty("hadoop.home.dir", path.toAbsolutePath().toString());
    }

    private static void processWordCount_Java8(Path filePath){
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("Word Count App");

        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> stringJavaRDD = javaSparkContext.textFile(filePath.toAbsolutePath().toString());

        JavaRDD<String> splitStringRDD = stringJavaRDD.flatMap( lines -> Arrays.asList(lines.split(" ")));

        JavaPairRDD<String, Integer> javaPairRDD = splitStringRDD.mapToPair( t -> new Tuple2<>(t, 1)).reduceByKey( (x,z) -> (int) x + (int)z );

        javaPairRDD.saveAsTextFile("spark-output");
    }
}