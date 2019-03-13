package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository
{
    private HashMap<Long, TimeEntry> timesheet = new HashMap<>();
    private long idCount = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        timesheet.put(idCount, timeEntry);
        timeEntry.setId(idCount);

        idCount++;
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timesheet.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> timeEntryList = new ArrayList<>();
        for (HashMap.Entry<Long, TimeEntry> timesheetEntry : timesheet.entrySet()) {
            timeEntryList.add(timesheetEntry.getValue());
        }
        return timeEntryList;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry currentEntry = this.find(id);
        if(currentEntry != null) {
            currentEntry.setDate(timeEntry.getDate());
            currentEntry.setHours(timeEntry.getHours());
            currentEntry.setProjectId(timeEntry.getProjectId());
            currentEntry.setUserId(timeEntry.getUserId());
        }

        return currentEntry;
    }

    @Override
    public void delete(long id) {
        TimeEntry currentEntry = this.find(id);
        timesheet.remove(currentEntry.getId());
    }
}
