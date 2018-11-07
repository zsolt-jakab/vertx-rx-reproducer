package examples;

import com.datastax.driver.core.Row;
import io.vertx.reactivex.cassandra.CassandraClient;
import io.vertx.reactivex.cassandra.CassandraRowStream;
import io.vertx.reactivex.cassandra.ResultSet;

import java.util.List;

public class RxCassandraClientExamples {

  public void connectPrepareExecuteAndDisconnect(CassandraClient cassandraClient) {
    cassandraClient.rxConnect()
      .andThen(cassandraClient.rxPrepare("SELECT * FROM my_keyspace.my_table where my_key = ?"))
      .flatMap(preparedStatement -> cassandraClient.rxExecute(preparedStatement.bind("my_value")))
      .flatMapMaybe(ResultSet::rxOne)
      .flatMapCompletable(row -> {
        // process the row here
        return cassandraClient.rxDisconnect();
      })
      .subscribe(() -> {
        System.out.println("We are done!");
      }, error -> {
        System.err.println("Whoops, something went wrong...");
        error.printStackTrace();
      });
  }

  public void simpleQueryStream(CassandraClient cassandraClient) {
    cassandraClient.rxQueryStream("SELECT my_key FROM my_keyspace.my_table where my_key = my_value ")
      .subscribe((CassandraRowStream stream) ->
        stream
          .handler(row -> System.out.println(row.getString("my_key")))
          .endHandler(end -> System.out.println("End of stream")));
  }

  public void simpleFullFetch(CassandraClient cassandraClient) {
    cassandraClient.rxExecuteWithFullFetch("SELECT my_key FROM my_keyspace.my_table where my_key = my_value ")
      .subscribe(
        (List<Row> results) -> results.forEach(row -> System.out.println(row.getString("my_key")))
      );
  }
}