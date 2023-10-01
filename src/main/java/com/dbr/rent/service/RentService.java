package com.dbr.rent.service;

import com.dbr.rent.Model.Owners;
import com.dbr.rent.Model.Tenants;
import com.dbr.rent.Model.User;
import com.dbr.rent.Utils.IDGenerator;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RentService {

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ResponseEntity<String> createUser(User user) throws Exception {

        String id = IDGenerator.generateRandomId(6);
        logger.log(Level.INFO, "Generated id: " + id);
        user.setUserId(id);
        logger.log(Level.INFO, user.toString());
        if (user.getUsertype() != null && user.getUsertype().equalsIgnoreCase("OWNER")) {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("Users").document(user.getUserId()).set(user);

            if (collectionsApiFuture.get().getUpdateTime() != null) {
                createOwner(user);
                String saveResponse = "id: " + user.getUserId();
                return new ResponseEntity<>(saveResponse, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }
        if (user.getUsertype() != null && user.getUsertype().equals("TENANT")) {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("Users").document(user.getUserId()).set(user);

            if (collectionsApiFuture.get().getUpdateTime() != null) {
                createTenants(user);
                String saveResponse = "id: " + user.getUserId();
                return new ResponseEntity<>(saveResponse, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public void createOwner(User user) throws Exception {
        Owners owners = new Owners();
        owners.setOwnerId(user.getUserId());
        owners.setUser(user);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("Owners").document(owners.getOwnerId()).set(owners);
        if (collectionsApiFuture.get().getUpdateTime() != null) {
            logger.log(Level.INFO, "Created Owner....");
        }
    }

    public void createTenants(User user) throws Exception {
        Tenants tenants = new Tenants();
        tenants.setTenantId(user.getUserId());
        tenants.setUser(user);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("Tenants").document(tenants.getTenantId()).set(tenants);
        if (collectionsApiFuture.get().getUpdateTime() != null) {
            logger.log(Level.INFO, "Created Tenants....");
        }
    }

}
