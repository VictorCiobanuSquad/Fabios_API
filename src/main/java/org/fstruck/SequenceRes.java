package org.fstruck;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.ArrayList;
import java.util.List;

@Path("/labseq")
public class SequenceRes {

    @Inject
    Service sequenceService;

    @GET
    @Path("/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Successfull", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.INTEGER)))
    @APIResponse(responseCode = "400", description = "Invalid", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING)))
    public Response getSequenceValue(@PathParam("n") int n) {
        try {
            long value = sequenceService.getValue(n);
            return Response.ok(value).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/cicle/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Successfull", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.INTEGER)))
    @APIResponse(responseCode = "400", description = "Invalid", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING)))
    public Response getSequenceCicleValue(@PathParam("n") int n) {
        try {
            long[] sequenceArray = sequenceService.getCicleValue(n);
            List<Long> sequenceList = new ArrayList<>();
            for (long value : sequenceArray) {
                sequenceList.add(value);
            }
            return Response.ok(sequenceList).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
