/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

/**
*
* The class {@code RoundRobinThread} defines a thread 
* that periodically(3minutes) execute the round robin algorithm.
*
**/

public class RoundRobinThread implements Runnable
{
	private final WineShopDatabase db;
	
	/**
	 * Class constructor.
	 *
	 * @param d the server database.
	 *
	**/
	public RoundRobinThread(final WineShopDatabase d)
	{
		this.db = d;
	}
	
	public void run() 
	{
		try {
			
			//at the server start no employees logged, wait a bit(10sec).
			Thread.sleep(10000); 
			
			while(true)
			{
				db.RoundRobin(); 
				Thread.sleep(180000); // sleep 3 minutes
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
