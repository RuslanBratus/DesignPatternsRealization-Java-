package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GitRepoObservers {
    public static Repository newRepository(){
        return new Repository() {
            final List<WebHook> hooks = new ArrayList<>();

            @Override
            public void addWebHook(WebHook webHook) { hooks.add(webHook); }

            @Override
            public Commit commit(String branch, String author, String[] changes) {
                Commit commit = new Commit(author, changes);
                Event event = new Event(Event.Type.COMMIT, branch, List.of(commit));
                hooks.forEach(hook -> hook.onEvent(event));
                return null;
            }

            @Override
            public void merge(String sourceBranch, String targetBranch) {
                if (!hooks.isEmpty())
                {
                        var commits = hooks.stream()
                                .filter (hook -> hook.branch().equals(sourceBranch) && hook.type().equals(Event.Type.COMMIT))
                                .flatMap(hook -> hook.caughtEvents().stream())
                                .flatMap(event -> event.commits().stream())
                                .collect(Collectors.toList());
                        var event = new Event(Event.Type.MERGE, targetBranch, commits);
                        hooks.forEach(hooks -> hooks.onEvent(event));

                }
            }
        };

    }

    public static WebHook mergeToBranchWebHook(String branchName){
        return new WebHookImplementation(branchName, Event.Type.MERGE);

    }

    static class WebHookImplementation implements WebHook
    {
        final String branch;
        final Event.Type type;
        final Set<Event> caughtEvents = new LinkedHashSet<>();

        private WebHookImplementation(String branch, Event.Type type)
        {
            this.branch = branch;
            this.type = type;
        }

        @Override
        public String branch() {
            return branch;
        }

        @Override
        public Event.Type type() {
            return type;
        }

        @Override
        public List<Event> caughtEvents() {
            return new ArrayList<>(caughtEvents);
        }

        @Override
        public void onEvent(Event event) {
            if (branch.equals(event.branch()) && type.equals(event.type())) {caughtEvents.add(event);}
        }


    }

    public static WebHook commitToBranchWebHook(String branchName){
        return new WebHookImplementation(branchName, Event.Type.COMMIT);
    }


}
