package com.six.tipsproject.plastic;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Xiaolin on 2016-07-22.
 */
public class EmployeeParcelable implements Parcelable {
    public int id;
    public String name;
    public String picture;
    public String about;
    public boolean[] isActivated = new boolean[]{false};

    public EmployeeParcelable(int id, String name, String picture, String about, boolean[] isActivated) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.about = about;
        this.isActivated = isActivated;
    }

    public EmployeeParcelable(Parcel source) {
        readFromParcel(source);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<EmployeeParcelable>() {

        @Override
        public EmployeeParcelable createFromParcel(Parcel source) {
            return new EmployeeParcelable(source);
        }

        @Override
        public EmployeeParcelable[] newArray(int size) {
            return new EmployeeParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(picture);
        dest.writeString(about);
        dest.writeBooleanArray(isActivated);
    }

    private void readFromParcel(Parcel source) {
        id = source.readInt();
        name = source.readString();
        picture = source.readString();
        about = source.readString();
        source.readBooleanArray(isActivated);
    }
}
