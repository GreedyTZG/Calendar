package cn.rygel.gd.bean.event.base;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.TimeZone;

import cn.rygel.gd.bean.event.constants.EventType;
import cn.rygel.gd.utils.calendar.LunarUtils;

public class BaseEvent implements Parcelable,MultiItemEntity {

    protected long mId = -1;

    protected String mName = "";
    protected String mDescription = "";
    protected String mUser = "";
    protected long mTimeZone = TimeZone.getDefault().getRawOffset();
    protected boolean mShowNotification = true;
    protected boolean mIsLunarEvent = false;
    protected long mStart = 0;
    protected LunarUtils.Solar mEventSolarDate = new LunarUtils.Solar();
    protected LunarUtils.Lunar mEventLunarDate = new LunarUtils.Lunar();
    protected EventType mEventType = null;

    /**
     * 提前提醒时间
     */
    protected long mAdvanceTime = 30 * 60 * 1000;

    /**
     * 延时时间，这个是点击稍后提醒以后下次提醒的时间
     */
    protected long mDelayTime = 5 * 60 * 1000;

    public BaseEvent(){ }

    public BaseEvent(Parcel in){
        setId(in.readLong());
        setAdvanceTime(in.readLong());
        setDelayTime(in.readLong());
        setName(in.readString());
        setDescription(in.readString());
        setUser(in.readString());
        setTimeZone(in.readLong());
        setShowNotification(in.readByte() == 1);
        setLunarEvent(in.readByte() == 1);
        setStart(in.readLong());
        setEventSolarDate(in.readParcelable(LunarUtils.Solar.class.getClassLoader()));
        setEventLunarDate(in.readParcelable(LunarUtils.Lunar.class.getClassLoader()));
        mEventType = in.readParcelable(EventType.class.getClassLoader());
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean isShowNotification() {
        return mShowNotification;
    }

    public void setShowNotification(boolean showNotification) {
        mShowNotification = showNotification;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getUser() {
        return mUser;
    }

    public void setUser(String user) {
        mUser = user;
    }

    public long getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(long timeZone) {
        mTimeZone = timeZone;
    }

    public boolean isLunarEvent() {
        return mIsLunarEvent;
    }

    public void setLunarEvent(boolean lunarEvent) {
        mIsLunarEvent = lunarEvent;
    }

    public long getStart() {
        return mStart;
    }

    public void setStart(long start) {
        mStart = start;
    }

    public LunarUtils.Solar getEventSolarDate() {
        return mEventSolarDate;
    }

    public void setEventSolarDate(LunarUtils.Solar eventSolarDate) {
        mEventSolarDate = eventSolarDate;
    }

    public LunarUtils.Lunar getEventLunarDate() {
        return mEventLunarDate;
    }

    public void setEventLunarDate(LunarUtils.Lunar eventLunarDate) {
        mEventLunarDate = eventLunarDate;
    }

    public long getAdvanceTime() {
        return mAdvanceTime;
    }

    public void setAdvanceTime(long advanceTime) {
        mAdvanceTime = advanceTime;
    }

    public long getDelayTime() {
        return mDelayTime;
    }

    public void setDelayTime(long delayTime) {
        mDelayTime = delayTime;
    }

    public void setEventType(EventType eventType) {
        mEventType = eventType;
    }

    public EventType getEventType() {
        return mEventType;
    }

    @Override
    public int getItemType() {
        return EventType.EVENT_TYPE_SUPPORT.indexOf(getEventType());
    }

    /****************************************** 支持Parcelable ****************************************/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(getId());
        dest.writeLong(getAdvanceTime());
        dest.writeLong(getDelayTime());
        dest.writeString(getName());
        dest.writeString(getDescription());
        dest.writeString(getUser());
        dest.writeLong(getTimeZone());
        dest.writeByte((byte) (isShowNotification() ? 1 : 0));
        dest.writeByte((byte) (isLunarEvent() ? 1 : 0));
        dest.writeLong(getStart());
        dest.writeParcelable(getEventSolarDate(),Parcelable.CONTENTS_FILE_DESCRIPTOR);
        dest.writeParcelable(getEventLunarDate(),Parcelable.CONTENTS_FILE_DESCRIPTOR);
        dest.writeParcelable(getEventType(),Parcelable.CONTENTS_FILE_DESCRIPTOR);
    }

    public static final Creator<BaseEvent> CREATOR = new Creator<BaseEvent>() {
        @Override
        public BaseEvent createFromParcel(Parcel source) {
            return new BaseEvent(source);
        }

        @Override
        public BaseEvent[] newArray(int size) {
            return new BaseEvent[size];
        }
    };

}
