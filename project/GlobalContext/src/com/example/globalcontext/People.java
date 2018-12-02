package com.example.globalcontext;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;
//public class People implements Serializable
public class People implements Parcelable{
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public People(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public People() {
	}
	
	
	/**
	 *  实现接口固定写法 
	 */
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(age);
	}
	
	public static final Parcelable.Creator<People> CREATOR = new Parcelable.Creator<People>() {

		@Override
		public People createFromParcel(Parcel source) {
			return new People(source.readString(), source.readInt());
		}

		@Override
		public People[] newArray(int size) {
			return new People[size];
		}
	};
}
