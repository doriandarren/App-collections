/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package entities;

import javax.persistence.*;

@Entity 
public class Item {    
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String description; 
    private String title;
    
    
    
    @ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="collection_id")
    Collection collection; 
    
  
    public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}


	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE},
			optional=false, orphanRemoval=true)
    Image image; 
    
    
    
    public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public String getId() { 
        return id;
    }

  
    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + id +
                ", bookTitle='" + title + '\'' +
                '}';
    }
}

