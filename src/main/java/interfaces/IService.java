package interfaces;

import spark.Request;
import spark.Response;

public interface IService {
    String getAll(Request request, Response response);
    String getById(Request request, Response response);
    String create(Request request, Response response);
    String update(Request request, Response response);
    String delete(Request request, Response response);
}
