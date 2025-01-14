package kz.zhandos.machinelearning.chapter3;

public class Pair<T, V> {
  public T first;
  public V second;

  Pair(T first, V second) {
    this.first = first;
    this.second = second;
  }

  public static <T, V> Pair<T, V> pair(T first, V second) {
    return new Pair<T, V>(first, second);
  }

  @Override
  public String toString() {
    return "Pair [first=" + first + ", second=" + second + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((first == null) ? 0 : first.hashCode());
    result = prime * result + ((second == null) ? 0 : second.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Pair other = (Pair) obj;
    if (first == null) {
      if (other.first != null)
        return false;
    } else if (!first.equals(other.first))
      return false;
    if (second == null) {
      if (other.second != null)
        return false;
    } else if (!second.equals(other.second))
      return false;
    return true;
  }



}
