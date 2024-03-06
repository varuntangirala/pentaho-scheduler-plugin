/*!
 *
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 *
 * Copyright (c) 2023 - 2024 Hitachi Vantara. All rights reserved.
 *
 */

package org.pentaho.platform.genericfile.providers.repository.model;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.pentaho.platform.genericfile.model.BaseGenericFileTree;
import org.pentaho.platform.genericfile.providers.repository.RepositoryFileProvider;

public class RepositoryFileTree extends BaseGenericFileTree {

  public RepositoryFileTree( RepositoryObject file ) {
    super( file );
  }

  @NonNull
  @Override
  public String getProvider() {
    return RepositoryFileProvider.TYPE;
  }

  @NonNull
  @Override
  public RepositoryObject getFile() {
    return (RepositoryObject) super.getFile();
  }
}
