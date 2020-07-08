package com.infoshareacademy.controller;

import com.infoshareacademy.service.RatingService;

import javax.ejb.EJB;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Path("/rate")
public class RatingController {


    @EJB
    RatingService ratingService;

    @POST
    @Path("/drinks/{drinkId}/rating/{rate}")
    public Response rateDrink(@PathParam("drinkId") long drinkId,
                              @PathParam("rate") double rate,
                              @Context HttpServletRequest req,
                              @Context HttpServletResponse res) {

        String ip = req.getRemoteAddr();

        if (isIpCookieEmpty(req)) {

            ratingService.updateRating(drinkId, rate);
            createCookie(res, ip);
            return Response.status(Response.Status.CREATED).build();
        } else {

            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private void createCookie(@Context HttpServletResponse res, String ip) {
        Cookie cookie = new Cookie("ip", ip);
        res.addCookie(cookie);
    }

    private boolean isIpCookieEmpty(@Context HttpServletRequest req) {
        return Arrays.stream(req.getCookies())
                .filter(c -> "ip".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny().isEmpty();
    }

}