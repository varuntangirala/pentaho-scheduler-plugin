/*!
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
 * Copyright (c) 2023 - 2024 Hitachi Vantara. All rights reserved.
 */

package org.pentaho.platform.api.genericfile;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.pentaho.platform.api.genericfile.exception.AccessControlException;
import org.pentaho.platform.api.genericfile.exception.InvalidPathException;
import org.pentaho.platform.api.genericfile.exception.OperationFailedException;
import org.pentaho.platform.api.genericfile.model.IGenericFileTree;

/**
 * The {@code IGenericFileService} interface contains operations to access and modify generic files.
 *
 * @see GenericFilePath
 * @see IGenericFileProvider
 * @see IGenericFileTree
 */
public interface IGenericFileService {
  /**
   * Clears the cache of folder trees for the current user session.
   *
   * @see #getFolderTree(GetTreeOptions)
   * @see #createFolder(GenericFilePath)
   */
  void clearFolderCache() throws OperationFailedException;

  /**
   * Gets a tree of folders.
   * <p>
   *
   * @param options The operation options. These control, for example, whether to return the full tree,
   *                a subtree of a given base path, as well as the depth of the returned folder tree,
   *                amongst other options.
   * @return The folder tree.
   * @throws AccessControlException   If user of the current session does not have permission to check the existence of
   *                                  the specified file.
   * @throws OperationFailedException If operation fails for any other (checked) reason.
   */
  @NonNull
  IGenericFileTree getFolderTree( @NonNull GetTreeOptions options ) throws OperationFailedException;

  /**
   * Checks whether a generic file exists, given its path.
   *
   * @param path The path of the generic file.
   * @return {@code true}, if the generic file exists; {@code false}, otherwise.
   * @throws AccessControlException   If user of the current session does not have permission to check the existence of
   *                                  the specified file.
   * @throws OperationFailedException If the operation fails for any other (checked) reason.
   */
  boolean doesFileExist( @NonNull GenericFilePath path ) throws OperationFailedException;

  /**
   * Checks whether a generic file exists, given its path's string representation.
   * <p>
   * The default implementation of this method parses the given path's string representation using
   * {@link GenericFilePath#parse(String)} and then calls {@link #doesFileExist(GenericFilePath)} with the result.
   *
   * @param path The string representation of the path of the generic file.
   * @return {@code true}, if the generic file exists; {@code false}, otherwise.
   * @throws InvalidPathException     If the specified path's string representation is not valid, according to
   *                                  {@link GenericFilePath#parse(String)}.
   * @throws AccessControlException   If user of the current session does not have permission to check the existence of
   *                                  the specified file.
   * @throws OperationFailedException If the operation fails for any other (checked) reason.
   */
  default boolean doesFileExist( @Nullable String path ) throws OperationFailedException {
    GenericFilePath genericPath = GenericFilePath.parse( path );
    return doesFileExist( genericPath );
  }

  /**
   * Creates a folder given its path.
   * <p>
   * This method ensures that each ancestor folder of the specified path exists,
   * creating it if necessary, and allowed.
   * <p>
   * When the operation is successful, the folder session cache for the generic file provider owning the folder is
   * automatically cleared.
   *
   * @param path The path of the generic folder to create.
   * @return {@code true}, if the folder did not exist and was created; {@code false}, if the folder already existed.
   * @throws AccessControlException   If the user of the current session does not have permission to create the folder.
   * @throws OperationFailedException If the operation fails for any other (checked) reason.
   */
  boolean createFolder( @NonNull GenericFilePath path ) throws OperationFailedException;

  /**
   * Creates a folder given its path's string representation.
   * <p>
   * This method ensures that each ancestor folder of the specified path exists,
   * creating it if necessary, and allowed.
   * <p>
   * The default implementation of this method parses the given path's string representation using
   * {@link GenericFilePath#parse(String)} and then calls {@link #createFolder(GenericFilePath)} with the result.
   * <p>
   * When the operation is successful, the folder session cache for the generic file provider owning the folder is
   * automatically cleared.
   *
   * @param path The string representation of the path of the generic folder to create.
   * @return {@code true}, if the folder did not exist and was created; {@code false}, if the folder already existed.
   * @throws InvalidPathException     If the specified path's string representation is not valid, according to
   *                                  {@link GenericFilePath#parse(String)}.
   * @throws AccessControlException   If the user of the current session does not have permission to create the folder.
   * @throws OperationFailedException If the operation fails for any other (checked) reason.
   */
  default boolean createFolder( @Nullable String path ) throws OperationFailedException {
    GenericFilePath genericPath = GenericFilePath.parse( path );
    return createFolder( genericPath );
  }
}
