package User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Arrays;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static Manager.Output.outputResponse.sendresponse;


@WebFilter("/*")
public class FrontEndAuthenticationFilter implements Filter {
    List<String> allowedPaths = Arrays.asList("/jauth", "/register", "/activate", "/dish", "/menu", "ingredient", "/Employee/Verification", "/Images", "/cart", "/order", "/notification", "/rider", "/goals", "/delivery", "/reset");
    List<String> customerPaths = Arrays.asList("/customize", "/mealplan", "/profile");

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


        String path = req.getServletPath();
//        System.out.println(path);

        if (allowedPaths.contains(path)) {
//            System.out.println("meka allowed");
//            System.out.println(path);
            chain.doFilter(request, response);


        } else {

            String btk = req.getHeader("authorization");
//            System.out.println(btk);
            if (btk != null) {
                String ea = btk.substring(btk.indexOf(' ') + 1);

                //            System.out.println(path);
                //
                //            System.out.println(btk);

                JWebToken incomingToken = null;

                try {
                    //                btk = req.getHeader("authorization");


                    //                System.out.println("im here now");
                    incomingToken = new JWebToken(ea);
                    //                System.out.println(incomingToken);
                } catch (NoSuchAlgorithmException e) {
                    //                System.out.println("im in catch");
                    e.printStackTrace();
                }
                if (incomingToken.isValid()) {
                    //                System.out.println("i came here");
                    List<String> aud = incomingToken.getAudience();

                    if (customerPaths.contains(path) && aud.contains("Customer")) {

                        chain.doFilter(request, response);
                    } else if (path.contains("/Manager/") && aud.contains("Manager")) {
                        chain.doFilter(request, response);
                    } else if (path.contains("/KitchenManager/") && aud.contains("Kitchen Manager")) {
                        chain.doFilter(request, response);
                    } else if (path.contains("/Supplier/") && aud.contains("Supplier")) {
                        chain.doFilter(request, response);
                    } else if (path.contains("/profile")) {
                        chain.doFilter(request, response);


                    } else if (path.contains("/profile")) {
                        chain.doFilter(request, response);
                    } else if (path.contains("/Cashier/") && aud.contains("Cashier")) {
                        chain.doFilter(request, response);

                    } else if (path.contains("/Chef/") && aud.contains("Chef")) {
                        chain.doFilter(request, response);
                    } else {
                        res.setStatus(401);
                    }
                    //                add customer paths here
                    //                else if(path.contains("/customer")){
                    //                    chain.doFilter(request, response);
                    //                }


                } else {
                    //                System.out.println("hello");
                    res.setStatus(401);
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } else {
//                sendresponse((HttpServletResponse) response,"{message:done}",404);
                res.setStatus(401);
            }


        }
    }


    public FrontEndAuthenticationFilter() {
//        System.out.println("kujll");
    }

    public void destroy() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}
