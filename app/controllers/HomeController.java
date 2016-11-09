package controllers;

import init.DbInit;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     *
     */

    public Result index() {
        return ok("Your new application is ready");
    }

    public Result testRoute(String line) {
        System.out.println(line);
        return ok("Hello Route!");
    }

    public Result testRoute2(String line1, String line2) {
        System.out.println(line1);
        System.out.println(line2);
        return ok("Hello Route2!");
    }

    public Result serviceTest() {
        DbInit.getInstance().assigmentTest();
        return ok("Service Test Complete.");
    }

}
