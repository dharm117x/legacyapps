package com.example.client.model.user;

import com.example.shared.model.user.UserTO;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface UserProps extends PropertyAccess<UserTO> {
 // Used by ListStore to uniquely identify rows
 @Path("id")
 ModelKeyProvider<UserTO> key();

 // Used by ColumnConfig to pull values into cells
 ValueProvider<UserTO, String> name();
 ValueProvider<UserTO, String> email();
}
