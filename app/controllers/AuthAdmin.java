package controllers;

import play.mvc.*;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletableFuture;

import models.users.*;

public class AuthAdmin extends Action.Simple{
    public CompletionStage<Result> call(Http.Context ctx){
        String id = ctx.session().get("username");
        if(id != null){
            User u = User.getUserById(id);
            if("admin".equals(u.getRole())){
                return delegate.call(ctx);
            }
        }
        return CompletableFuture.completedFuture(redirect(routes.LoginController.login()));
    }
}