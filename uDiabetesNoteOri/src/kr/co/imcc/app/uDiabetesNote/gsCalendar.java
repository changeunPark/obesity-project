package kr.co.imcc.app.uDiabetesNote;


import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.graphics.drawable.*;
import android.util.Log;
import android.view.*;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;



/*

������ : ���ϳ� (���)
���� ��¥ : 2011.02.26
�÷��� : ���� 7 64bit, ��Ŭ����, �ȵ���̵� SDK(2.1 ver7)

/**/
/*
������Ʈ : 2011.03.12
���� : �������� �߰��Ͽ� ���� ���� ����
������ ��¥�� �۾��� ���� ����̹��� ���� ����

������Ʈ : 2011.06.20
���� : �� ���ο� 14�Ͼ� ǥ���ϴ� �͵� �ϰ� ������ ��ƴٴ� ���� �־
�ּ��� �߰��ϰ� 4�� 14ĭ�� �غý��ϴ�.
���θ�� �ؼ� ������ �����ϴ°� �� �˾Ƽ�....

���� �̰ͺ��� �� ���� �ڼ��ϱ�� ������ ������ �ϴµ� -_-;
/**/
public class gsCalendar extends Activity 
{
    /// �翬�� �̾߱����� ���ٿ� 7�Ͼ� �ȵ� ��� ����ó���� ������� �ʾҴ� -_-;;
	final static int ROWS = 7 ; /// ��/���
	final static int COLS = 7 ; /// ĭ/����
	
//	final static int ROWS = 4 ; /// ��/���
//	final static int COLS = 14 ; /// ĭ/����
//	
	
	Context m_context ;				/// context 
	
	LinearLayout m_targetLayout ;	/// �޷��� ���� ���̾ƿ�
	Button [] m_controlBtn ;		/// �޷� ��Ʈ���� ��ư 4�� [���⵵, �����⵵, ����, ������]
	TextView [] m_viewTv ;			/// �� �� �� ǥ���� �ؽ�Ʈ�� 3��[��, �� , ��]
	
	Calendar m_Calendar ;			/// ����� �޷�
	
	LinearLayout [ ] m_lineLy ;		/// 7���� ����ǥ�� + �ִ� 6��
    LinearLayout [ ] m_cellLy ;		/// 7ĭ
    TextView [ ] m_cellTextBtn ;	/// �� ĭ���� ���� �ؽ�Ʈ�� (��ưó�� �̺�Ʈ �ַ��� Btn �̶� �ٿ���)
    								/// ��� ��ư���� �ϰ������ ��ư�� �ؽ�Ʈ ������ �׾ �����¿� ������ ����
    								/// �ؽ�Ʈ�� ©���� TextView�� ���� �� �ۿ� ����
    
    LinearLayout [ ] m_horizontalLine ; /// ��輱 ���� ����
    LinearLayout [ ] m_verticalLine ;	/// ��輱 ���� ����
    
    int m_startPos ;				/// ������ ��� ���� �� ��ġ
    int m_lastDay ;					/// �� ���� ��������
    int m_selDay ;					/// ���� ���õ� ��¥
    
	////////////////////////////////////////    	
    
    float m_displayScale ;			/// ȭ�� ����� ���� �ؽ�Ʈ ũ�� ������ �����
    float m_textSize ;				/// �ؽ�Ʈ ������(�� ������ ������ ������)
    float m_topTextSize ;			/// �����ؽ�Ʈ ������(���� �������� ������)
    
    int m_tcHeight = 50 ;			/// ���� ���� �κ� ��ĭ�� ����
    int m_cWidth = 50 ;				/// ��ĭ�� ����
    int m_cHeight = 50 ;			/// ��ĭ�� ����
    int m_lineSize = 1 ;			/// ��輱�� ����
    
    static public class gsCalendarColorParam
    {
    	int m_lineColor 			= 0xff000000 ;	/// ��輱 ��
        int m_cellColor 			= 0xffffffff ;	/// ĭ�� ����
        int m_topCellColor 			= 0xffcccccc ;	/// ���� ����
        int m_textColor 			= 0xff000000 ;	/// �۾���
        int m_sundayTextColor 		= 0xffff0000 ;	/// �Ͽ��� �۾���
        int m_saturdayTextColor 	= 0xff0000ff ;	/// ����� �۾���
        int m_topTextColor 			= 0xff000000 ; 	/// ���� �۾���
        int m_topSundayTextColor 	= 0xffff0000 ; 	/// ���� �Ͽ��� �۾���
        int m_topSaturdatTextColor 	= 0xff0000ff ; 	/// ���� ����� �۾���
        
        int m_todayCellColor		= 0x999999ff ;	/// ���ó�¥�� ����
        int m_todayTextColor		= 0xffffffff ;  /// ���ó�¥�� �۾���
    }
    
    gsCalendarColorParam m_colorParam ;
    
    /// ������ �����ϰ� ������ bgcolor�� ó����( ���� ���������� )
    Drawable m_bgImgId = null ;				/// ��ü ����̹���
    Drawable m_cellBgImgId = null ;			/// ��ĭ�� ��� �̹���
    Drawable m_topCellBgImgId = null ;		/// ��� ���� ���� �κ��� ��� �̹���
    
    Drawable m_todayCellBgImgId = null ; 	/// ���� ��¥�� ��� �̹���
    
    /// ��ܿ� ǥ���ϴ� ���� �ؽ�Ʈ
    String [] m_dayText ={ "��", "��", "ȭ", "��", "��", "��", "��" } ; 
    
    ///////////////////////////////////////////
    
    Button m_preYearBtn ;			/// ���⵵ ��ư
    Button m_nextYearBtn ;			/// �����⵵ ��ư
    Button m_preMonthBtn ;			/// ���� ��ư
    Button m_nextMonthBtn ;			/// ������ ��ư
    
    TextView m_yearTv ;				/// �� ǥ�ÿ� �ؽ�Ʈ
    TextView m_mothTv ;				/// �� ǥ�ÿ� �ؽ�Ʈ
    TextView m_dayTv ;				/// ��¥ ǥ�ÿ� �ؽ�Ʈ
    
    
    /// ������ MMdd�������� �ִ´�.
    /// ������ 2�� 4 5 6�̶��
    /// [0204] [0205] [0206] �̷��� ����
    ArrayList< Integer > m_holiDay = new ArrayList< Integer >( ) ;
    
	
    /// ������
	public gsCalendar( Context context, LinearLayout layout )
	{
		/// context����
		m_context = context ;
		
		/// Ÿ�� ���̾ƿ� ����
		m_targetLayout = layout ;
		
		/// ���� ��¥�� �޷� ����
		m_Calendar = Calendar.getInstance( ) ;
		
		/// ǥ���� ������ ���̾� ����
		m_lineLy = new LinearLayout[ COLS ] ;						/// 7���� ���̾ƿ� ����
        m_cellLy = new LinearLayout[ COLS * ROWS ] ;				/// �׾ȿ�  �ٴ� 7���� �� 49���� ���̾ƿ� ����
        m_cellTextBtn = new TextView[ COLS * ROWS ] ;				/// ������ ������ ��ư�� ����
        m_horizontalLine = new LinearLayout[ COLS - 1 ] ; 			/// ���� ���м� ���̾ƿ�
        m_verticalLine = new LinearLayout[ ( COLS - 1 ) * ROWS ] ;	/// ���� ���м� ���̾ƿ�
        
        /// ȭ���� ũ�⿡ ���� ������
        m_displayScale = context.getResources( ).getDisplayMetrics( ).density ;
        
        m_topTextSize = m_displayScale * 12.0f ;
        m_textSize = m_displayScale * 12.0f ;
        
        m_colorParam = new gsCalendarColorParam( ) ;
        
        
//        ROWS = 7 ; /// ��/���
//    	COLS = 14 ; /// ĭ/����
	}
	
	/// �޷��� �����Ѵ�.( ��� �ɼǵ�[�÷���, �ؽ�Ʈ ũ�� ��]�� ������ �Ŀ� �������� ��� �� �� ȣ��)
	public void initCalendar( )
	{
		createViewItem( ) ;
        setLayoutParams( ) ;
        setLineParam( ) ;
        setContentext( ) ;
        setOnEvent( ) ;
        printView( ) ;
	}
	
	/// �÷��� �Ķ���� ����
	public void setColorParam( gsCalendarColorParam param )
	{
		m_colorParam = param ;
	}
	
	/// ������� �� �̹����� ����
	public void setBackground( Drawable bg )
	{
		m_bgImgId = bg ;				
	}
	public void setCellBackground( Drawable bg )
	{
	    m_cellBgImgId = bg ;			
	}
	public void setTopCellBackground( Drawable bg )
	{
		m_topCellBgImgId = bg ;			
	}
	
	public void setCalendarSize( int width, int height  )
	{
		m_cWidth = ( width - ( m_lineSize * COLS - 1 ) ) / COLS ;
        m_cHeight = ( height - ( m_lineSize * ROWS - 1 ) ) / ROWS ;
        m_tcHeight = ( height - ( m_lineSize * COLS - 1 ) ) / COLS ;
	}
	
	public void setCellSize( int cellWidth, int cellHeight, int topCellHeight  )
	{
		m_cWidth = cellWidth ;
        m_cHeight = cellHeight ;
		m_tcHeight = topCellHeight ;
	}
	
	public void setTopCellSize( int topCellHeight  )
	{
		m_tcHeight = topCellHeight ;
	}
	
	public void setCellSize( int allCellWidth, int allCellHeight )
	{
		m_cWidth = allCellWidth ;
        m_cHeight = allCellHeight ;
		m_tcHeight = allCellHeight ;
	}
	
	public void setTextSize( float size )
	{
		m_topTextSize = m_displayScale * size ;
        m_textSize = m_displayScale * size ;
	}
	
	public void setTextSize( float textSize, float topTextSize )
	{
		m_topTextSize = m_displayScale * topTextSize ;
        m_textSize = m_displayScale * textSize ;
	}
	
	
	public void redraw( )
	{
		m_targetLayout.removeAllViews( ) ;
		initCalendar( ) ;
		
	}
	
	
	//////////////////// ������ ��¥ĭ�� ��ȭ�� �ִ� �Լ� //////////////////////////
	/// �̳༮�� �ҷ������� ���´� ��¥�� ���÷� ���õǾ��ְų� ���� �������� ����
	/// �׷����� m_cellLy[ ��¥ + m_startPos ].setTextColor( ) ;
	/// m_startPos�� ������ ������ ��¥�� ���ϸ� �ش� ��¥ĭ�� ������� �ٲ� �� ���� 
	/// ////////////////////////////////////////////////////////////////////
	/// ���õ� ��¥ĭ�� ��ȭ�� �ֱ����� �Լ� 1ȣ
	public void setSelectedDay( int cellColor, int textColor )
	{
		m_colorParam.m_todayCellColor = cellColor ;
		m_colorParam.m_todayTextColor = textColor ;
		m_cellTextBtn[ m_Calendar.get( Calendar.DAY_OF_MONTH ) + m_startPos - 1 ].setTextColor( textColor ) ;
		m_cellTextBtn[ m_Calendar.get( Calendar.DAY_OF_MONTH ) + m_startPos - 1 ].setBackgroundColor( cellColor ) ;
	}
	
	/// ���õ� ��¥ĭ�� ��ȭ�� �ֱ����� �Լ� 2ȣ
	public void setSelectedDayTextColor( int textColor )
	{
		m_colorParam.m_todayTextColor = textColor ;
		m_cellTextBtn[ m_Calendar.get( Calendar.DAY_OF_MONTH ) + m_startPos - 1 ].setTextColor( textColor ) ;
	}
	
	/// ���õ� ��¥ĭ�� ��ȭ�� �ֱ����� �Լ� 3ȣ
	public void setSelectedDay( Drawable bgimg )
	{
		m_todayCellBgImgId = bgimg ;
		m_colorParam.m_todayCellColor = 0x00000000 ;
		m_cellTextBtn[ m_Calendar.get( Calendar.DAY_OF_MONTH ) + m_startPos - 1 ].setBackgroundDrawable( bgimg ) ;
		Log.d("===",(m_Calendar.get( Calendar.DAY_OF_MONTH ) -1)+ "" ) ;
	}
	
	
	///////////////////////////// ������ ó�� ///////////////////////
	/// ������ MMdd�������� �ִ´�.
    /// ������ 2�� 4 5 6�̶��
    /// [0204] [0205] [0206] �̷��� ����
	public void addHoliday( int holiday_MMdd )
	{
		m_holiDay.add( holiday_MMdd ) ;
	}
	
	/// ������ ����Ʈ�� �������鼭 �ش� ��¥�� �Ͽ��ϰ� ���� ������ ����
	public void applyHoliday( )
	{
		/// ���� �޷��� ���� ����
		Integer iMonth = m_Calendar.get( Calendar.MONTH ) + 1 ;
		
		/// ���Ϸ� ����� ��� ��¥ ���� ���� ����
		for( int k = 0 ; k < m_holiDay.size( ) ; k++ )
		{
			int holiday = m_holiDay.get( k ) ;	/// ���� ���� ���Ѵ��� 
			if( holiday / 100 == iMonth )		/// ���� ������ ���
			{
				/// �ش� ��¥�� ���� �÷��� ����
				m_cellTextBtn[ holiday % 100 + m_startPos ].setTextColor( m_colorParam.m_sundayTextColor ) ;
			}
		}
	}
	
	
	
	
	/// ������ ����ŭ ���� �������� ��¥ ǥ��Ǵ� �� + ��輱 �ټ�
	public void createViewItem( )
	{
		/// 7���̸� ��輱 ���α��� ���ؼ� 13���� �����ؾ��Ѵ�.
        for( int i = 0 ; i < ROWS * 2 - 1 ; i++ )
        {
        	/// ¦�������϶��� 
        	if( i % 2 == 0 )
        	{
        		/// ������ 13�� ���������� ������ ǥ�õǴ� ������ 7����
	        	m_lineLy[i/2] = new LinearLayout( m_context ) ;
	        	m_targetLayout.addView( m_lineLy[i/2] ) ; // ���� ���̾ƿ��� �ڽ����� ���
        	
	        	/// ��¥ǥ�� ĭ�� ��輱 ���ؼ� 13���� ĭ�� ����
	        	for( int j = 0 ; j < COLS * 2 - 1 ; j++ )
	        	{
	        		
	        		/// �޷� ������ ���� ĭ
	        		if( j % 2 == 0 )
		        	{
	        			int pos = ( ( i / 2 ) * COLS ) + ( j / 2 ) ;
	        			
	        			Log.d( "pos1", "" +  pos ) ;
		        		m_cellLy[ pos ] = new LinearLayout( m_context ) ;
		        		m_cellTextBtn[ pos ] = new TextView( m_context ) ;
		        		m_lineLy[ i / 2 ].addView( m_cellLy[ pos ] ) ;
		        		m_cellLy[ pos ].addView( m_cellTextBtn[ pos ] ) ;
		        		
		        	}
	        		else	/// �̰� �ܼ��� ��輱
	        		{
	        			int pos = ( ( i / 2 ) * (COLS - 1) ) + ( j - 1 ) / 2 ;
	        			
	        			Log.d( "pos2", "" +  pos ) ;
	        			m_verticalLine[ pos ] = new LinearLayout( m_context ) ;
		        		m_lineLy[ i / 2 ].addView( m_verticalLine[ pos ] ) ;
	        		}
	        	}
        	}
        	else	/// �̰� ������ ��輱
        	{
        		m_horizontalLine[ ( i - 1 ) / 2 ] = new LinearLayout( m_context ) ;
	        	m_targetLayout.addView( m_horizontalLine[ ( i - 1 ) / 2 ] ) ;
	        	
	        	
        	}
        }
	}
	
	/// ���̾ƿ��� ��ư�� ����, �۾��� �� ViewParams�� ����
	public void setLayoutParams( )
	{
		/// ���� ���̾ƿ��� ���η� ����
		m_targetLayout.setOrientation( LinearLayout.VERTICAL ) ;
		/// ���� ��ü ����� ������ �־���
		if( m_bgImgId != null )
		{
			m_targetLayout.setBackgroundDrawable( m_bgImgId ) ;
		}
		
		/// ������ ����ŭ ���� �������� ��¥ ǥ��Ǵ� �� + ��輱 �ټ�
		for( int i = 0 ; i < ROWS * 2 - 1 ; i++ )
		{
			if( i % 2 == 0 )
        	{
				/// �� ������ �����ϴ� ���̾ƿ����� ���η� ����~
				m_lineLy[i/2].setOrientation( LinearLayout.HORIZONTAL ) ;
				m_lineLy[i/2].setLayoutParams(	/// ���̾ƿ� ������� warp_content�� ���� 
						new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT ) ) ;
	        	
				/// ��ĭ��ĭ �ɼ��� ����
	        	for( int j = 0 ; j < COLS ; j++ )
	        	{
	        		int cellnum = ( ( i / 2 ) * COLS ) + j ;
	        		/// ��ĭ��ĭ�� �����ϴ� ���̾ƿ� ������� ���� wrap_content�� ���� 
	        		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT ) ;
	        		//param.setMargins( 1, 1, 1, 1 ) ;	/// ������ 1�� �༭ ������ �׸���.
	        		m_cellLy[ cellnum ].setLayoutParams( param ) ;
	        		/// ��ĭ��ĭ ���� ��ư
	        		m_cellTextBtn[ cellnum ].setGravity( Gravity.CENTER ) ;
	        		
	        		
	        		/// ���ϴ� ���� �۾��� �۾� ũ�� �����ϴ� �κ�
	        		
	        		
	        		/// ù������ ��ȭ��������� ǥ���ϴ� �κ�
	        		if( i == 0 )	
	        		{
	        			/// ���� ǥ���ϴ� �κ��� ���� ����
	        			m_cellTextBtn[ cellnum ].setLayoutParams( new LinearLayout.LayoutParams( m_cWidth, m_tcHeight ) ) ;
	        			
	        			/// ���� �۾���
	        			if( m_topCellBgImgId != null )
	        			{
	        				m_cellLy[ cellnum ].setBackgroundDrawable( m_topCellBgImgId ) ;
	        			}
	        			else
	        			{
	        				m_cellLy[ cellnum ].setBackgroundColor( m_colorParam.m_topCellColor ) ;
	        			}
	        			
	        			/// ����ϰ� �Ͽ����� �ٸ� �÷��� ǥ���Ѵ�.
	            		switch( j )
	    	    		{
	    	    		case 0:
	    	    			m_cellTextBtn[ cellnum ].setTextColor( m_colorParam.m_topSundayTextColor ) ;
	    	    			break ;
	    	    		case 6:
	    	    			m_cellTextBtn[ cellnum ].setTextColor( m_colorParam.m_topSaturdatTextColor ) ;
	    	    			break ;
	    	    		default:
	    	    			m_cellTextBtn[ cellnum ].setTextColor( m_colorParam.m_topTextColor ) ;
	    	    			break ;
	    	    		}
	            		
	            		/// �۾� ũ��
	            		m_cellTextBtn[ cellnum ].setTextSize( m_topTextSize ) ;
	        		}
	        		else			/// ���ϴ� ��¥ ǥ���ϴ� �κ�
	        		{
	        		
	        			/// ���� ǥ�õǴ� �κ��� ���̿� ����
	        			m_cellTextBtn[ cellnum ].setLayoutParams( new LinearLayout.LayoutParams( m_cWidth, m_cHeight ) ) ;
	        			
	        			/// bg�� �۾���
	        			if( m_cellBgImgId != null )
	        			{
	        				m_cellLy[ cellnum ].setBackgroundDrawable( m_cellBgImgId ) ;
	        			}
	        			else
	        			{
	        				m_cellLy[ cellnum ].setBackgroundColor( m_colorParam.m_cellColor ) ;
	        			}
	        			
	        			/// ����ϰ� �Ͽ����� �ٸ� �÷��� ǥ���Ѵ�.
	            		switch( j )
	    	    		{
	    	    		case 0:
	    	    			m_cellTextBtn[ cellnum ].setTextColor( m_colorParam.m_sundayTextColor ) ;
	    	    			break ;
	    	    		case 6:
	    	    			m_cellTextBtn[ cellnum ].setTextColor( m_colorParam.m_saturdayTextColor ) ;
	    	    			break ;
	    	    		default:
	    	    			m_cellTextBtn[ cellnum ].setTextColor( m_colorParam.m_textColor ) ;
	    	    			break ;
	    	    		}
	            		
	            		/// �۾� ũ��
	            		m_cellTextBtn[ cellnum ].setTextSize( m_textSize ) ;
	        		}
	        		
	        		
	        	}
        	}
		}
	}
	
	public void setLineParam( )
	{
		for( int i = 0 ; i < ROWS - 1 ; i ++ )
		{
			m_horizontalLine[ i ].setBackgroundColor( m_colorParam.m_lineColor ) ;	/// ���λ�
			m_horizontalLine[ i ].setLayoutParams(	/// ���� �����̴ϱ� ���δ� �� ���δ� �β���ŭ 
						new LinearLayout.LayoutParams( LayoutParams.FILL_PARENT, m_lineSize ) ) ;
		}
		for( int i = 0 ; i < ROWS ; i ++ )
		{
			for( int j = 0 ; j < COLS - 1 ; j++ )
	    	{
	    		int pos = ( i * ( COLS - 1 ) ) + j ;
	    		m_verticalLine[ pos ].setBackgroundColor( m_colorParam.m_lineColor ) ; /// ���λ�
	    		m_verticalLine[ pos ].setLayoutParams(	/// ���� �����̴ϱ� ���δ� ��~ ���δ� �β���ŭ 
						new LinearLayout.LayoutParams( m_lineSize, LayoutParams.FILL_PARENT ) ) ;
	    	}
		}
	}
	
	/// �޷��� �����ϴ� �� �� ���� �����ϱ�
	public void setContentext( )
	{
		/// �޷��� �ϳ� �����ؼ� �۾��Ѵ�.
		Calendar iCal = (Calendar) m_Calendar.clone( ) ;
		
		/// ��¥�� ��~
		m_selDay = iCal.get( Calendar.DATE ) ;
		
		/// ��¥�� 1�� �����Ͽ� ���� 1���� ���� �������� ����
		iCal.set( Calendar.DATE, 1 ) ;
		/// ����ǥ���ϴ� �� �� 7ĭ + ������ ù �����ϴ� ĭ��
		m_startPos = COLS + iCal.get( Calendar.DAY_OF_WEEK ) - Calendar.SUNDAY ;
		
		/// 1�� ���ؼ� ������ 1�Ϸ� ������ٰ� 1���� ���� ���� ���������� ������
		iCal.add( Calendar.MONTH, 1 ) ;
		iCal.add( Calendar.DATE, -1 ) ;
		
        m_lastDay = iCal.get( Calendar.DAY_OF_MONTH ) ;         /// �ش� ���� �������� ��~   
        
        /// 0���� 6��ĭ������ ��ȭ���������~ �� ä������
		for( int k = 0 ; k < COLS ; k++ )
    	{
			m_cellTextBtn[ k ].setText(  m_dayText[ k % 7 ] ) ;
    	}
		
		/// 7������ ó�� ������ġ �������� �������� ä��
		for( int i = COLS ; i < m_startPos ; i++ )
		{
			m_cellTextBtn[ i ].setText( "" ) ;
		}

		/// ������ġ���ʹ� 1���� �ؼ� ���� ������������ ���ڷ� ä��
		for( int i = 0 ; i < m_lastDay ; i++ )
		{
			m_cellTextBtn[ i + m_startPos ].setText( ( i + 1 ) + "" ) ;
		}
		
		/// ������������ �������� �������� ä��
		for( int i = m_startPos + m_lastDay ; i < COLS * ROWS ; i++ )
		{
			m_cellTextBtn[ i ].setText( "" ) ;
		}
	}
	
	/// �� ��ư�鿡 setOnClickListener �ֱ�
	public void setOnEvent( )
	{
		/// ��ȭ��������� ���ִ� �κп��� ������ ������ �ʿ� ����
		for( int i = COLS ; i < COLS * ROWS ; i++ )
		{
			final int k = i ;
			m_cellTextBtn[i].setOnClickListener( new Button.OnClickListener( ) 
			{
				@Override
				public void onClick(View v) 
				{
					
					if( m_cellTextBtn[k].getText( ).toString( ).length() > 0 )
					{
						m_Calendar.set( Calendar.DATE, Integer.parseInt( m_cellTextBtn[k].getText( ).toString( ) ) ) ;
						if( m_dayTv != null )
			    			m_dayTv.setText( m_Calendar.get( Calendar.DAY_OF_MONTH ) + "" ) ;
						printView( ) ;
						myClickEvent( 	m_Calendar.get( Calendar.YEAR ),
										m_Calendar.get( Calendar.MONTH ),
										m_Calendar.get( Calendar.DAY_OF_MONTH ) ) ;
					}
				}
			} ) ;
		}
	}
	
	/// �޷��� ��� ���� �� �� ���� �������
	public void printView( )
	{
		/// �ؽ�Ʈ ����� ������ �� �ؽ�Ʈ �信�ٰ� �� �� ���� �������
		if( m_yearTv != null )
			m_yearTv.setText( m_Calendar.get( Calendar.YEAR ) + "" ) ;
		if( m_mothTv != null )
		{
			//int imonth =  iCal.get( Calendar.MONTH ) ;
			m_mothTv.setText( ( m_Calendar.get( Calendar.MONTH ) + 1 ) + "" ) ;
		}
		if( m_dayTv != null )
			m_dayTv.setText( m_Calendar.get( Calendar.DAY_OF_MONTH ) + "" ) ;

	} 
	
	/// �⵵�� ���� ��~ ��~��
	public void preYear( )
	{
		m_Calendar.add( Calendar.YEAR, -1 ) ;
		setContentext( ) ;
		printView( ) ;
	}
	public void nextYear( )
	{
		m_Calendar.add( Calendar.YEAR, 1 ) ;
		setContentext( ) ;
		printView( ) ;
	}
	public void preMonth( )
	{
		m_Calendar.add( Calendar.MONTH, -1 ) ;
		setContentext( ) ;
		printView( ) ;
	}
	public void nextMonth( )
	{
		m_Calendar.add( Calendar.MONTH, 1 ) ;
		setContentext( ) ;
		printView( ) ;
	}    	
	
	/// �ؽ�Ʈ�並 �־��ָ� ���� �ѷ��� (��� ��������� �ȻѸ�)
	public void setViewTarget( TextView [] tv ) 
	{
		m_yearTv = tv[0] ;
		m_mothTv = tv[1] ;
        m_dayTv = tv[2] ;
	}
        
	/// ��ư�� �־��ָ� �˾Ƽ� �ɼ� �־��� (���ó� ��� ������ �̺�Ʈ �ȳ���)
	public void setControl( Button [] btn )
	{
		m_preYearBtn = btn[0] ;
        m_nextYearBtn = btn[1] ;
        m_preMonthBtn = btn[2] ;
        m_nextMonthBtn = btn[3] ;
        
        if( m_preYearBtn != null )
           m_preYearBtn.setOnClickListener( new Button.OnClickListener( ) 
           {
				@Override
				public void onClick(View v) 
				{
					preYear( ) ;
				}
			} ) ;
        if( m_nextYearBtn != null )
            m_nextYearBtn.setOnClickListener( new Button.OnClickListener( ) 
            {
				@Override
				public void onClick(View v) 
				{
					nextYear( ) ;
				}
			} ) ;
        if( m_preMonthBtn != null )
            m_preMonthBtn.setOnClickListener( new Button.OnClickListener( ) 
            {
				@Override
				public void onClick(View v) 
				{
					preMonth( ) ;
				}
			} ) ;
        if( m_nextMonthBtn != null )
            m_nextMonthBtn.setOnClickListener( new Button.OnClickListener( ) 
            {
				@Override
				public void onClick(View v) 
				{
					nextMonth( ) ;
				}
			} ) ;
	}
	
	/// ���ϴ� ������ ��¥�� ������ 
	/// ��) 
	/// String today = getData( "yyyy-MM-dd" )�̷���?
	public String getData( String format )
	{
		SimpleDateFormat sdf = new SimpleDateFormat( format, Locale.US ) ;
		return sdf.format( new Date( m_Calendar.getTimeInMillis( ) ) ) ;
	}
	
	/// �޷¿��� ��¥�� Ŭ���ϸ� �� �Լ��� �θ���.
	public void myClickEvent( int yyyy, int MM, int dd )
	{
		Log.d( "yyyy", "" + yyyy ) ;
		Log.d( "MM", "" + MM ) ;
		Log.d( "dd", "" + dd ) ;
	}
    
	public int pixelToDip( int arg )
	{
		m_displayScale = m_context.getResources( ).getDisplayMetrics( ).density ;
		return (int) ( arg * m_displayScale ) ;
	}
	
	public gsCalendarColorParam getBasicColorParam( )
	{
		return new gsCalendarColorParam( ) ;
	}
}

