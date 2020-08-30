import Foundation

extension Array where Element: Numeric & Comparable {
    var prevPermutation: [Element] {
        var array = self
        
        var i = array.count-1
        while i > 0 && array[i-1] <= array[i] {
            i -= 1
        }
        
        if i <= 0 { return [] }
        
        var j = array.count-1
        while array[j] >= array[i-1] { j -= 1 }
        
        var temp = array[i-1]
        array[i-1] = array[j]
        array[j] = temp
        
        j = array.count-1
        while i < j {
            temp = array[i]
            array[i] = array[j]
            array[j] = temp
            
            i+=1
            j-=1
        }
        
        return array
    }
    
    
    var nextPermutation: [Element] {
        var array = self
        
        var i = array.count-1
        while i > 0 && array[i-1] >= array[i] {
            i -= 1
        }
        
        if i <= 0 { return [] }
        
        var j = array.count-1
        while array[j] <= array[i-1] { j -= 1 }
        
        var temp = array[i-1]
        array[i-1] = array[j]
        array[j] = temp
        
        j = array.count-1
        while i < j {
            temp = array[i]
            array[i] = array[j]
            array[j] = temp
            
            i+=1
            j-=1
        }
        
        return array
    }
}
