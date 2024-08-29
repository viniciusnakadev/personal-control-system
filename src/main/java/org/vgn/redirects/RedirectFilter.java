package org.vgn.redirects;

import java.io.IOException;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class RedirectFilter implements ContainerRequestFilter {

    @Inject
    @ConfigProperty(name = "quarkus.http.root-path")
    String basePath;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getUriInfo().getPath().isEmpty()) {
            requestContext.abortWith(Response.status(Response.Status.MOVED_PERMANENTLY)
                    .header("Location", basePath)
                    .build());
        }
    }

}
