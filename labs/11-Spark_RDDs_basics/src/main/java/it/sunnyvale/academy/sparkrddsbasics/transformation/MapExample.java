package it.sunnyvale.academy.sparkrddsbasics.transformation;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/*
Run me:

$ mvn exec:exec -Dspark.master=local -P MapExample

$ spark-submit \ --master yarn \ --class it.sunnyvale.academy.sparkrddsbasics.transformation.MapExample \ --deploy-mode cluster \ target/spark-rdds-basics-1.0-SNAPSHOT.jar

 */
public class MapExample {

    /*
     * mapToUppercase
     */
    public static void mapToUppercase(JavaSparkContext sc){
        List<String> list = Arrays.asList("Java","Scala","Hello world", "Java helps", "ITS ICT");
        JavaRDD<String> collectionRDD = sc.parallelize(list);
        JavaRDD<String> mappedRDD = collectionRDD.map(s -> s.toUpperCase());
        List<String> uppercaseList  = mappedRDD.collect();
        for (String element : uppercaseList) {
            System.out.println(element);
        }

    }



    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("MapExamples");
        JavaSparkContext sc = new JavaSparkContext(conf);

        mapToUppercase(sc);
    }
}
