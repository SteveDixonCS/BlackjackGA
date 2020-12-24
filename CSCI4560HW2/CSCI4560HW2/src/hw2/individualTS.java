package hw2;
import java.util.Random;
import java.lang.Math; 

public class individualTS {
	
	node[] DNA;
	Random RNG = new Random();;
	int[] isSelected;	
	
	individualTS()
	{
		
		DNA = new node[127];
		isSelected = new int[127];
		node[] cityList = createCityList();
		//initialize isSelected
		for(int i = 0; i < isSelected.length; i++)
		{
			isSelected[i] = 0;
		}
		
		for(int i = 0; i < DNA.length; i++)
		{
			//while index is empty
			while(DNA[i]== null)
			{
				//generate a random number
				int z = RNG.nextInt(DNA.length);
				//if city node position is not selected then assign for dna index
				if(isSelected[z] == 0)
				{
					DNA[i] = cityList[z];
					//change value to one to indicate city node has been selected
					isSelected[z] = 1;
				}
			}
		}
	}
	
	double fitness(individualTS individual)
	{
		int minimumPossibleDistance = 118282;
		int maxInt = 2147483647;
		double distTraveled = 0;
		double euclideanDist = 0;
		double fitness;
		
		for(int i = 0; i < individual.DNA.length; i++)
		{
			//add last index to first index
			if(i == individual.DNA.length - 1)
			{
				double y2 = individual.DNA[0].y;
				double y1 = individual.DNA[i].y;
				double x2 = individual.DNA[0].x;
				double x1 = individual.DNA[i].y;
				
				double calc = ((y2-y1)*(y2-y1))+((x2-x1)*(x2-x1));
				
				euclideanDist = Math.sqrt(calc);
				
				distTraveled += euclideanDist;
				break;
			}
			
			double y2 = individual.DNA[i+1].y;
			double y1 = individual.DNA[i].y;
			double x2 = individual.DNA[i+1].x;
			double x1 = individual.DNA[i].y;
			
			double calc = ((y2-y1)*(y2-y1))+((x2-x1)*(x2-x1));
			
			euclideanDist = Math.sqrt(calc);
			
			distTraveled += euclideanDist;
		}
		
		if(distTraveled == 0)
		{
			System.out.print("ERROR CALCULATING FITNESS VALUE");
			fitness = 1/maxInt;
		}
		else
		{
			fitness = minimumPossibleDistance/distTraveled;
			
			if(fitness == 1)
			{
				System.out.println("SOLUTION FOUND");
				for( int i = 0; i < individual.DNA.length; i++)
				{
					System.out.println("Node: " + i + " X is: " + individual.DNA[i].x + " Y is: " + individual.DNA[i].y );
				}
				System.exit(0);
			}
		}
		
		return fitness;
	}
	
	final node[] createCityList()
	{
		
		node[] cityList = new node[127];
		
		for(int i = 0;i< 127;i++)
		{
			cityList[i] = new node();
		}
		
		cityList[0].x =  9860; cityList[0].y =14152;
		cityList[1].x = 9396; cityList[1].y = 14616;
		cityList[2].x = 14616; cityList[2].y = 14848;
		cityList[3].x = 11020 ; cityList[3].y = 13456;
		cityList[4].x = 9512; cityList[4].y = 15776 ;
		cityList[5].x =  10788; cityList[5].y = 13804;
		cityList[6].x =  10208; cityList[6].y = 14384;
		cityList[7].x = 11600; cityList[7].y =  13456;
		cityList[8].x = 11252; cityList[8].y = 14036;
		cityList[9].x = 10672; cityList[9].y = 15080;
		cityList[10].x = 11136 ; cityList[10].y = 14152;
		cityList[11].x = 9860; cityList[11].y = 13108;
		cityList[12].x = 10092; cityList[12].y =14964 ;
		cityList[13].x = 9512 ; cityList[13].y = 13340;
		cityList[14].x =  10556 ; cityList[14].y = 13688 ;
		cityList[15].x = 9628; cityList[15].y = 14036;
		cityList[16].x =   10904 ; cityList[16].y = 13108;
		cityList[17].x =11368 ; cityList[17].y = 12644;
		cityList[18].x =11252 ; cityList[18].y = 13340;
		cityList[19].x =  10672; cityList[19].y =13340 ;
		cityList[20].x =  11020; cityList[20].y = 13108;
		cityList[21].x =  11020; cityList[21].y =  13340;
		cityList[22].x = 11136 ; cityList[22].y = 13572;
		cityList[23].x = 11020; cityList[23].y = 13688;
		cityList[24].x = 8468; cityList[24].y = 11136;
		cityList[25].x = 8932 ; cityList[25].y =  12064;
		cityList[26].x = 9512  ; cityList[26].y = 12412;
		cityList[27].x = 7772   ; cityList[27].y = 11020;
		cityList[28].x =  8352; cityList[28].y = 10672;
		cityList[29].x = 9164 ; cityList[29].y = 12876;
		cityList[30].x =  9744; cityList[30].y = 12528 ;
		cityList[31].x = 8352; cityList[31].y = 10324;
		cityList[32].x =  8236; cityList[32].y = 11020;
		cityList[33].x =  8468; cityList[33].y = 12876;
		cityList[34].x =  8700; cityList[34].y = 14036;
		cityList[35].x =  8932; cityList[35].y =  13688 ;
		cityList[36].x =  9048; cityList[36].y = 13804 ;
		cityList[37].x = 8468  ; cityList[37].y = 12296;
		cityList[38].x = 8352 ; cityList[38].y = 12644 ;
		cityList[39].x =  8236 ; cityList[39].y = 13572;
		cityList[40].x =   9164 ; cityList[40].y =  13340;
		cityList[41].x =  8004; cityList[41].y =  12760 ;
		cityList[42].x =   8584 ; cityList[42].y = 13108;
		cityList[43].x =  7772 ; cityList[43].y = 14732;
		cityList[44].x =  7540; cityList[44].y =  15080;
		cityList[45].x =  7424; cityList[45].y =  17516;
		cityList[46].x =  8352 ; cityList[46].y = 17052;
		cityList[47].x =  7540; cityList[47].y = 16820;
		cityList[48].x = 7888  ; cityList[48].y =  17168;
		cityList[49].x =  9744; cityList[49].y = 15196;
		cityList[50].x = 9164; cityList[50].y = 14964 ;
		cityList[51].x = 9744 ; cityList[51].y = 16240 ;
		cityList[52].x = 7888; cityList[52].y = 16936;
		cityList[53].x = 8236; cityList[53].y = 15428;
		cityList[54].x = 9512 ; cityList[54].y =  17400;
		cityList[55].x =  9164 ; cityList[55].y = 16008;
		cityList[56].x = 8700 ; cityList[56].y =15312 ;
		cityList[57].x = 11716  ; cityList[57].y =16008 ;
		cityList[58].x = 12992; cityList[58].y = 14964;
		cityList[59].x = 12412 ; cityList[59].y = 14964;
		cityList[60].x =  12296; cityList[60].y =  15312;
		cityList[61].x = 12528 ; cityList[61].y =  15196;
		cityList[62].x =  15312; cityList[62].y = 6612;
		cityList[63].x = 11716 ; cityList[63].y = 16124;
		cityList[64].x = 11600; cityList[64].y =  19720;
		cityList[65].x =10324 ; cityList[65].y =  17516;
		cityList[66].x =  12412  ; cityList[66].y = 13340;
		cityList[67].x =12876  ; cityList[67].y = 12180;
		cityList[68].x = 13688; cityList[68].y = 10904;
		cityList[69].x = 13688; cityList[69].y = 11716;
		cityList[70].x = 13688; cityList[70].y = 12528 ;
		cityList[71].x = 11484 ; cityList[71].y = 13224;
		cityList[72].x = 12296; cityList[72].y = 12760;
		cityList[73].x = 12064 ; cityList[73].y =  12528;
		cityList[74].x = 12644  ; cityList[74].y =10556 ;
		cityList[75].x = 11832; cityList[75].y = 11252;
		cityList[76].x = 11368 ; cityList[76].y = 12296 ;
		cityList[77].x = 11136; cityList[77].y = 11020;
		cityList[78].x = 10556 ; cityList[78].y = 11948;
		cityList[79].x = 10324 ; cityList[79].y =  11716;
		cityList[80].x =  11484 ; cityList[80].y =9512 ;
		cityList[81].x = 11484 ; cityList[81].y =  7540;
		cityList[82].x = 11020; cityList[82].y =  7424;
		cityList[83].x = 11484 ; cityList[83].y = 9744;
		cityList[84].x = 16936; cityList[84].y = 12180;
		cityList[85].x = 17052; cityList[85].y = 12064;
		cityList[86].x = 16936; cityList[86].y = 11832;
		cityList[87].x = 17052 ; cityList[87].y = 11600;
		cityList[88].x =  13804; cityList[88].y =  18792;
		cityList[89].x = 12064; cityList[89].y =  14964;
		cityList[90].x = 12180; cityList[90].y = 15544 ;
		cityList[91].x =  14152; cityList[91].y = 18908;
		cityList[92].x = 5104; cityList[92].y = 14616;
		cityList[93].x = 6496  ; cityList[93].y = 17168;
		cityList[94].x = 5684; cityList[94].y = 13224;
		cityList[95].x = 15660 ; cityList[95].y =  10788;
		cityList[96].x = 5336; cityList[96].y =  10324;
		cityList[97].x =  812; cityList[97].y = 6264;
		cityList[98].x =  14384; cityList[98].y = 20184;
		cityList[99].x = 11252; cityList[99].y = 15776;
		cityList[100].x = 9744 ; cityList[100].y = 3132;
		cityList[101].x = 10904; cityList[101].y = 3480;
		cityList[102].x = 7308 ; cityList[102].y = 14848;
		cityList[103].x = 16472 ; cityList[103].y = 16472;
		cityList[104].x =  10440  ; cityList[104].y = 14036;
		cityList[105].x = 10672; cityList[105].y = 13804;
		cityList[106].x = 1160; cityList[106].y = 18560;
		cityList[107].x =  10788 ; cityList[107].y =  13572;
		cityList[108].x = 15660; cityList[108].y = 11368;
		cityList[109].x = 15544; cityList[109].y = 12760;
		cityList[110].x = 5336 ; cityList[110].y =18908 ;
		cityList[111].x =   6264; cityList[111].y =19140 ;
		cityList[112].x = 11832; cityList[112].y = 17516;
		cityList[113].x = 10672 ; cityList[113].y = 14152;
		cityList[114].x = 10208  ; cityList[114].y = 15196;
		cityList[115].x = 12180; cityList[115].y = 14848;
		cityList[116].x = 11020; cityList[116].y = 10208;
		cityList[117].x = 7656; cityList[117].y = 17052;
		cityList[118].x = 16240 ; cityList[118].y = 8352;
		cityList[119].x =  10440; cityList[119].y = 14732;
		cityList[120].x = 9164 ; cityList[120].y = 15544;
		cityList[121].x = 8004  ; cityList[121].y =  11020;
		cityList[122].x =  5684; cityList[122].y =  11948 ;
		cityList[123].x = 9512; cityList[123].y =  16472;
		cityList[124].x = 13688; cityList[124].y = 17516;
		cityList[125].x = 11484; cityList[125].y =8468 ;
		cityList[126].x = 3248; cityList[126].y =  14152;


		
		return cityList;
	}
}
