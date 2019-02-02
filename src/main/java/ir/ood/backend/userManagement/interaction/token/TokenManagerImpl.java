package ir.ood.backend.userManagement.interaction.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.inject.Inject;
import ir.ood.backend.ConfigurationConstants;
import ir.ood.backend.commons.exceptions.ForbiddenException;
import ir.ood.backend.commons.exceptions.InternalException;
import ir.ood.backend.userManagement.domain.Worker;
import ir.ood.backend.userManagement.domain.WorkerRepo;

import javax.annotation.Nonnull;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.logging.Logger;

public class TokenManagerImpl implements TokenManager {

    @Inject
    private ConfigurationConstants constants;
    @Inject
    private WorkerRepo repo;

    @Override
    public String createToken(Worker worker) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(constants.getJwtSecret());
            return JWT.create().
                    withClaim("id", worker.getId()).
                    sign(algorithm);
        } catch (UnsupportedEncodingException | JWTCreationException exception) {
            Logger.getLogger(getClass().getName()).warning(exception.getMessage());
            throw new InternalException("jwt", "Could not make jwt");
        }
    }

    @Override
    @Nonnull
    public Worker findWorker(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Integer id = jwt.getClaim("id").asInt();
            if (id == null)
                throw new ForbiddenException("jwt", "invalid_token");
            Optional<Worker> worker = repo.findById(id);
            return worker.orElseThrow(() -> new ForbiddenException("jwt", "Worker with this token is not found"));
        } catch (JWTDecodeException exception) {
            throw new ForbiddenException("jwt", "could not extract id from token");
        }
    }
}
