package com.jarvanmo.jlibrary.rxbus;

import android.support.annotation.NonNull;

import com.jarvanmo.jlibrary.util.JLog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * Created by mo on 16-3-26.
 * an event bus written with RxJava
 */
public class RxBus {

    private static final String TAG = RxBus.class.getSimpleName();
    private static RxBus instance;
    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();


    public static synchronized RxBus provider() {
        if (null == instance) {
            instance = new RxBus();
        }
        return instance;
    }


    public <T> Observable<T> register(@NotNull Object tag, @NotNull Class<T> clazz) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }

        Subject<T, T> subject;
        subjectList.add(subject = PublishSubject.create());
        JLog.d(TAG, "[register]subjectMapper: " + subjectMapper);
        return subject;
    }

    public void unregister(@NonNull Object tag, @NonNull Observable observable) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove(observable);
            subjectMapper.remove(tag);
        }

        JLog.d(TAG, "[unregister]subjectMapper: " + subjectMapper);
    }

    public void post(@NonNull Object content) {
        post(content.getClass().getName(), content);
    }

    @SuppressWarnings("unchecked")
    public void post(@NonNull Object tag, @NonNull Object content) {
        List<Subject> subjectList = subjectMapper.get(tag);


        for (Subject subject : subjectList) {
            subject.onNext(content);
        }

        JLog.d(TAG, "[send]subjectMapper: " + subjectMapper);
    }

    private RxBus() {
        throw new AssertionError("No instances.");
    }


}
