/**
 * Utility functions javascript
 * 
 */


/**
 *  THis function format a date with prototype.yyymmdd.
 */

Date.prototype.yyyymmdd = function(month) {
	var mm = this.getMonth() + month; // getMonth() is zero-based
	var dd = this.getDate();
	return [this.getFullYear(), '-' + (mm>9 ? '' : '0') + mm,'-' + (dd>9 ? '' : '0') + dd].join('');
  };


  /**
   *  THis function return date in last month.
   */  
function monthLast(dateNow){

     
    var miliseconds = parseInt(24*60*60*1000);
    var dateBefore = new Date();
    var timeNow = dateNow.getTime();
    var days = 30;
    dateBefore.setTime( parseInt(  timeNow - (days * miliseconds )) );
   return dateBefore;
}