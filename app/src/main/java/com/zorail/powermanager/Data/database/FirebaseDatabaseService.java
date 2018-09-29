package com.zorail.powermanager.Data.database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;

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
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class FirebaseDatabaseService implements DataBaseSource {

    private static final String BOARD_DETAILS = "b_details";
    private static final String USER_DETAILS = "user_details";
    private static final String USAGE = "usage";

    public static FirebaseDatabaseService getInstance() {
        return new FirebaseDatabaseService();
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
    public Observable<Usage> getUsageDetails(final String phone) {
        return Observable.create(
                new ObservableOnSubscribe<Usage>() {
                    @Override
                    public void subscribe(final ObservableEmitter<Usage> emitter) throws Exception {
                        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference userRef = rootRef.child(USAGE).child(phone);
                        userRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    Usage usage = dataSnapshot.getValue(Usage.class);
                                    emitter.onNext(usage);
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
    public Observable<BoardDetails> getBoardDetails(final String boardId) {
        Log.d("BoardId", boardId);
        return Observable.create(
                new ObservableOnSubscribe<BoardDetails>() {
                    @Override
                    public void subscribe(final ObservableEmitter<BoardDetails> emitter) throws Exception {
                        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference boardRef = rootRef.child(BOARD_DETAILS).child(boardId);
                        boardRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    BoardDetails details = dataSnapshot.getValue(BoardDetails.class);
                                    emitter.onNext(details);
                                } else {
                                    emitter.onComplete();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                emitter.onError(databaseError.toException());
                            }
                        });
                    }
                }
        );
    }

    @Override
    public Observable<Pair<Usage, BoardDetails>> combinedUsageBoardDetails(String phone) {
        return this.getUsageDetails(phone)
                .flatMap(new Function<Usage, ObservableSource<Pair<Usage, BoardDetails>>>() {
                    @Override
                    public ObservableSource<Pair<Usage, BoardDetails>> apply(Usage usage) throws Exception {
                        return Observable.just(usage).zipWith(
                                getBoardDetails(String.valueOf(usage.getB_id())),
                                new BiFunction<Usage, BoardDetails, Pair<Usage, BoardDetails>>() {
                                    @Override
                                    public Pair<Usage, BoardDetails> apply(Usage usage, BoardDetails boardDetails) throws Exception {
                                        return new Pair<>(usage, boardDetails);
                                    }
                                });
                    }
                });
    }

}
