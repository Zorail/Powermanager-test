package com.zorail.powermanager.Data.database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zorail.powermanager.Data.BoardDetails;
import com.zorail.powermanager.Data.Usage;
import com.zorail.powermanager.Data.User;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;

public class FirebaseDatabaseService implements DataBaseSource {

    private static final String BOARD_DETAILS = "b_details";
    private static final String USER_DETAILS = "user_details";
    private static final String USAGE = "usage";

    public static FirebaseDatabaseService getInstance() {
        return new FirebaseDatabaseService();
    }

    @Override
    public Maybe<BoardDetails> getBoardDetails(final String phone) {
        return Maybe.create(
                new MaybeOnSubscribe<BoardDetails>() {
                    @Override
                    public void subscribe(final MaybeEmitter<BoardDetails> e) throws Exception {
                        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                        final DatabaseReference idRef = rootRef.child(BOARD_DETAILS).child(phone);
                        idRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    BoardDetails boardDetails = dataSnapshot.getValue(BoardDetails.class);
                                    e.onSuccess(boardDetails);
                                } else {
                                    e.onComplete();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                e.onError(databaseError.toException());
                            }
                        });
                    }
                }
        );
    }

    @Override
    public Maybe<User> getUserDetails(final String phone) {
        return Maybe.create(
                new MaybeOnSubscribe<User>() {
                    @Override
                    public void subscribe(final MaybeEmitter<User> emitter) throws Exception {
                        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference userRef = rootRef.child(USER_DETAILS).child(phone);
                        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()) {
                                    User user = dataSnapshot.getValue(User.class);
                                    emitter.onSuccess(user);
                                } else {
                                    emitter.onComplete();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                emitter.onError(databaseError.toException());
                            }
                        });
                    }
                }
        );
    }

    @Override
    public Maybe<Usage> getUsageDetails(final String phone) {
        return Maybe.create(
                new MaybeOnSubscribe<Usage>() {
                    @Override
                    public void subscribe(final MaybeEmitter<Usage> emitter) throws Exception {
                        Log.d("TAg2", "here");
                        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference userRef = rootRef.child(USAGE).child(phone);
                        userRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    Usage usage = dataSnapshot.getValue(Usage.class);
                                    Log.d("Usage", usage.toString());
                                    emitter.onSuccess(usage);
                                } else {
                                    emitter.onComplete();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                    emitter.onError(databaseError.toException());
                                    Log.d("Tag2", databaseError.toString());
                            }
                        });
                    }
                }
        );
    }
}
