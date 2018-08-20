import org.apache.spark.sql.{DataFrame, SparkSession}

/** Running the main using implicit methods**/
object TestImplicits {

  def main(args: Array[String]): Unit = {
    // Should always define what it is
    val spark: SparkSession = createSparkSession()
    // where reg === 99 I need to return the r_id number
    readCsv(spark, "./src/main/resources/test.csv")
    .getInvalidData()
    .printInvalidValues()
  }

  /** Creates a Spark Session
    *
    * @return Spark = SparkSession
    */
  def createSparkSession(): SparkSession = {
    val spark: SparkSession = SparkSession.builder().master("local").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    spark
  }

  /** Reads in a csv file to a dataframe
    *
    * @param spark
    * @param path
    * @return df = dataframe of input data
    */
  def readCsv(spark: SparkSession, path: String): DataFrame = {
    val df: DataFrame = spark.read.option("header", true).csv(path)
    println("Input Data")
    df.show()
    df
  }





  implicit class ImplicitMethods(df: DataFrame) {
    // Expecting dataframe from the environment

    /** Identifies the ids which are missing data
      *
      * @param df
      * @return df = dataframe containing ids that have missing data/ a reg of 99
      */
    def getInvalidData(): DataFrame = {
      df.where("reg = 99").select("r_id")
    }

    /** Prints the ids that are missing values for reg
      *
      * @param df 
      */
    def printInvalidValues(): Unit = {
      println("Missing data in ")
      // map goes through the list
      // for every record make desired changes
      // return string at position 0 in each row
      // foreach prints each string in the list
      df.collect().toList.map(r => r.getString(0)).foreach(println)
    }
  }
}
