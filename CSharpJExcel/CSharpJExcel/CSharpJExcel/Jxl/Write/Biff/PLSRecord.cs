/*********************************************************************
*
*      Copyright (C) 2002 Andrew Khan
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
***************************************************************************/

// Port to C# 
// Chris Laforet
// Wachovia, a Wells-Fargo Company
// Feb 2010


using CSharpJExcel.Jxl.Biff;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * Record which specifies a print header for a work sheet
	 */
	public class PLSRecord : WritableRecordData
		{
		/**
		 * The binary data
		 */
		private byte[] data;

		/**
		 * Consructor invoked when copying a spreadsheet
		 * 
		 * @param hr the read header record
		 */
		public PLSRecord(CSharpJExcel.Jxl.Read.Biff.PLSRecord hr)
			: base(Type.PLS)
			{
			data = hr.getData();
			}

		/**
		 * Consructor invoked when copying a sheets
		 * 
		 * @param hr the read header record
		 */
		public PLSRecord(PLSRecord hr)
			: base(Type.PLS)
			{
			data = new byte[hr.data.Length];
			System.Array.Copy(hr.data, 0, data, 0, data.Length);
			}

		/**
		 * Gets the binary data for output to file
		 * 
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			return data;
			}
		}
	}


