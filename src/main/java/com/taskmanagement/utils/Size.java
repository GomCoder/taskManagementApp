package com.taskmanagement.utils;

import java.util.Objects;

/**
 * 이미지 사이즈 설정
 */
public class Size {
  private final int width;
  private final int height;

  public Size(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Size)) return false;
    Size size = (Size) o;
    return width == size.width &&
          height == size.height;
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height);
  }

  @Override
  public String toString() {
    return  width + "x" + height;
  }
}
