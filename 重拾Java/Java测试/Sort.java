//或说归并算法
public class Main{

  /**
  归并排序的思想:归并排序是基于归并这个简单测操作,即将两个有序的数组合并成一个更大的有序数组,递归方式是自上而下的
  */
  public void merge(int[] arr,int low,int high,int arrtemp){
    for(int i =low;i<=high;i++){
      arrtemp[i]=arr[i];
    }
    int i = low;
    int mid =(low+high)/2;
    int j = mid+1;
    for(int k =low,k<=high;k++){
      if(i>mid)
        arr[k] = arrtemp[j++];
      else if(j>high)
        arr[k] = arrtemp[i++];
      else if(arrtemp[i]<arrtemp[j])
        arr[k] = arrtemp[i++];
      else
        arr[k] = arrtemp[j++];
    }
  }
  public void gSort(int[] arr){
    int[] arrtemp = new int[arr.length];

    gSort(arr,0,arr.length-1,arrtemp);
  }
  private void gSort(int[] arr,int low,int high,int[] arrtemp){
    if(low>=high){
      return;
    }
    int mid = (low+high)/2;
    gSort(arr,low,mid,arrtemp);
    gSort(arr,mid+1,high,arrtemp);
    merge(arr,low,high,arrtemp);
  }
  /**
  快速排序
  快速排序是一种分治的排序方法,他将一个数组分亨两个子数组,将两个独立的部分排序
  */
  private int partCut(int[] arr,int low,int high){
    int i =low;
    int j =high+1;
    int cut = arr[low];//通常默认第一个点为切分元素
    while(true){
      while(arr[++i]<cut) if(i==high) break;
      while(cut<arr[--j]);
      if(i>=j) break;
      swap(arr,i,j);
    }
    swap(arr,low,j);
    return j ;
  }
  public void quickSort(int[] arr){
    quick(arr,0,arr.length-1);
  }
  private void quick(int[] arr,int low,int high){
    if(low>=high) return;
    int j = partCut(arr,low,high);
    quick(arr,low,j-1);
    quick(arr,j+1,high);
  }
}
